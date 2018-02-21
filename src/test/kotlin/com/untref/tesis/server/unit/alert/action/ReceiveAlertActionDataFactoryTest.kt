package com.untref.tesis.server.unit.alert.action

import com.untref.tesis.server.alert.action.ReceiveAlertActionData
import com.untref.tesis.server.alert.action.factory.*
import com.untref.tesis.server.alert.action.validator.CoordinateValidator
import com.untref.tesis.server.alert.action.validator.LatitudeValidator
import com.untref.tesis.server.alert.action.validator.LongitudeValidator
import com.untref.tesis.server.builders.*
import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Coordinate
import com.untref.tesis.server.alert.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ReceiveAlertActionDataFactoryTest {

    private lateinit var fireAlertDto: FireAlertDto
    private var exception: RuntimeException? = null
    private lateinit var receiveAlertActionDataFactory: ReceiveAlertActionDataFactory
    private var receiveAlertActionData: ReceiveAlertActionData? = null

    @Before
    fun setUp() {
        val coordinateValidator = CoordinateValidator(LatitudeValidator(), LongitudeValidator())
        receiveAlertActionDataFactory = ReceiveAlertActionDataFactory(coordinateValidator)
    }

    @Test
    fun negativeGasThrowException() {
        givenFireAlertDto(gas = -1f)
        whenTryCreateActionData()
        thenExpectedException(gasCanNotBeNegative)
    }

    @Test
    fun nullGasThrowsException() {
        givenFireAlertDto(gas = null)
        whenTryCreateActionData()
        thenExpectedException(gasCanNotBeNull)
    }

    @Test
    fun nullTemperatureThrowException() {
        givenFireAlertDto(temperature = null)
        whenTryCreateActionData()
        thenExpectedException(temperatureCanNotBeNull)
    }

    @Test
    fun nullDetectionMethodsThrowException() {
        givenFireAlertDto(detectionMethods = null)
        whenTryCreateActionData()
        thenExpectedException(detectionMethodsCanNotBeNull)
    }

    @Test
    fun emptyDetectionMethodThrowException() {
        givenFireAlertDto(detectionMethods = listOf())
        whenTryCreateActionData()
        thenExpectedException(detectionMethodsCanNotBeEmpty)
    }

    @Test
    fun nullCoordinatesThrowsException() {
        givenFireAlertDto(coordinates = null)
        whenTryCreateActionData()
        thenExpectedException(coordinatesCanNotBeNull)
    }

    @Test
    fun validAlertReturnActionData() {
        givenFireAlertDto()
        whenTryCreateActionData()
        thenCreateActionData()
    }

    private fun thenCreateActionData() {
        assertNotNull(receiveAlertActionData)
        assertCoordinate(south, defaultDegree, defaultMinute, defaultSecond, receiveAlertActionData?.coordinates?.latitude)
        assertCoordinate(east, defaultDegree, defaultMinute, defaultSecond, receiveAlertActionData?.coordinates?.longitude)
        assertEquals(defaultDetectionMethods, receiveAlertActionData?.detectionMethods)
        assertEquals(defaultTemperature, receiveAlertActionData?.temperature)
        assertEquals(defaultGas, receiveAlertActionData?.gas)
    }

    private fun assertCoordinate(cardinalPoint: CardinalPoint, degree: Int, minute: Int, second: Double, coordinate: Coordinate?) {
        assertEquals(cardinalPoint, coordinate?.cardinalPoint)
        assertEquals(degree, coordinate?.degree)
        assertEquals(minute, coordinate?.minute)
        assertEquals(second, coordinate?.second)
    }

    private fun givenFireAlertDto(coordinates: CoordinatesDto? = createCoordinatesDto(), detectionMethods: List<DetectionMethod>? = defaultDetectionMethods,
                                  temperature: Float? = defaultTemperature, gas: Float? = defaultGas) {
        fireAlertDto = createFireAlertDto(coordinates, detectionMethods, temperature, gas)
    }

    private fun thenExpectedException(errorMessage: String) {
        Assert.assertNotNull(exception)
        Assert.assertEquals(errorMessage, exception?.message)
    }

    private fun whenTryCreateActionData() {
        try {
            receiveAlertActionData = receiveAlertActionDataFactory.create(fireAlertDto)
        } catch (e: RuntimeException) {
            exception = e
        }
    }
}