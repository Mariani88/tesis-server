package com.untref.tesis.server.integration.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.untref.tesis.server.Application
import com.untref.tesis.server.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder
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

    private lateinit var mvc: MockMvc

    private var mapper = ObjectMapper()

    private lateinit var coordinates: CoordinatesDto
    private lateinit var detectionMethods: List<DetectionMethod>
    private val temperature = 60F
    private val gas = 700F

    @Before
    fun setUp() {
        mvc = webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun testa() {
        coordinates = CoordinatesDto("40°-50'-30''", "50°-30'-10''")
        detectionMethods = listOf(DetectionMethod.FIRE)
        val fireAlertDto = FireAlertDto(coordinates, detectionMethods, temperature, gas)
        val json = mapper.writeValueAsString(fireAlertDto)
        mvc.perform(post("/alert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk)
    }
}