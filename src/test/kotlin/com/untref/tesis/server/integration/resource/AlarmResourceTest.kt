package com.untref.tesis.server.integration.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.untref.tesis.server.Application
import com.untref.tesis.server.domain.AlertRepository
import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [Application::class])
@WebAppConfiguration
class AlarmResourceTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var alertRepository: AlertRepository

    private lateinit var mvc: MockMvc

    private var mapper = ObjectMapper()

    private val latitudeDto = CoordinateDto(30, 50, 23.4f, CardinalPoint.SOUTH)
    private val longitudeDto = CoordinateDto(30, 50, 23.4f, CardinalPoint.EAST)
    private val coordinates = CoordinatesDto(latitudeDto, longitudeDto)
    private val detectionMethods = listOf(DetectionMethod.FIRE)
    private val temperature = 60F
    private val gas = 700F

    private lateinit var fireAlertDto: FireAlertDto
    private lateinit var response: ResultActions

    @Before
    fun setUp() {
        mvc = webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun sendAlertShouldReturn200Ok() {
        givenAlert()
        whenSendAlert()
        thenExpect200Ok()
    }

    @Test
    fun sendAlertShouldStoreIt() {
        givenAlert()
        whenSendAlert()
        thenAlertIsStored()
    }

    private fun thenAlertIsStored() {
        val alert = alertRepository.find(1)
        assertNotNull(alert)
    }

    private fun givenAlert() {
        fireAlertDto = FireAlertDto(coordinates, detectionMethods, temperature, gas)
    }

    private fun whenSendAlert() {
        val json = mapper.writeValueAsString(fireAlertDto)
        response = mvc.perform(post("/alert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
    }

    private fun thenExpect200Ok() = response.andExpect(status().isOk)
}