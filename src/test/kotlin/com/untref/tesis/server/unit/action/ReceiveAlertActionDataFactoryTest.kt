package com.untref.tesis.server.unit.action

import com.untref.tesis.server.action.ReceiveAlertActionDataFactory
import com.untref.tesis.server.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ReceiveAlertActionDataFactoryTest {

    private lateinit var fireAlertDto: FireAlertDto
    private val temperature = 30f
    private val gas = 400f
    private val detectionMethods = listOf(DetectionMethod.FIRE)
    private lateinit var coordinates: CoordinatesDto

    @JvmField
    @Rule
    val expectedException: ExpectedException = ExpectedException.none()

    /*@Test
    fun gasCanNotNegative() {
        givenFireAlertDto(gas = null)
        thenCreateActionDateThrowsException("gas can not null")
    }

    @Test
    fun gasCanNotBeNull() {
        fail()
    }

    @Test
    fun temperatureCanNotBeNull() {
        fail()
    }

    @Test
    fun detectionMethodsCanNotBeNull() {
        fail()
    }

    @Test
    fun detectionMethodCanNotBeEmpty() {
        fail()
    }

    @Test
    fun coordinatesCanNotBeNull() {
        fail()
    }

    private fun thenCreateActionDateThrowsException(message: String) {
        expectedException.expect(RuntimeException::class.java)
        expectedException.expectMessage(message)
        ReceiveAlertActionDataFactory.create(fireAlertDto)
    }*/

    private fun givenFireAlertDto(coordinates: CoordinatesDto? = this.coordinates, detectionMethods: List<DetectionMethod>? = this.detectionMethods,
                                  temperature: Float? = this.temperature, gas: Float? = this.gas) = FireAlertDto(coordinates, detectionMethods,
            temperature, gas)
}