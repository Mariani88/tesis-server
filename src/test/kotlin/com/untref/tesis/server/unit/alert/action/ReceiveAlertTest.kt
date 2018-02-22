package com.untref.tesis.server.unit.alert.action

import com.untref.tesis.server.alert.action.*
import com.untref.tesis.server.builders.CoordinatesBuilder
import com.untref.tesis.server.alert.domain.*
import com.untref.tesis.server.extensions.MockitoExtensions
import com.untref.tesis.server.notification.domain.AlertNotificationService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import com.untref.tesis.server.unit.alert.repository.InMemoryAlertRepository
import org.assertj.core.api.Assertions.assertThat

class ReceiveAlertTest {

    private val detectionMethods = listOf(DetectionMethod.TEMPERATURE, DetectionMethod.FIRE)
    private val coordinatesActionData = CoordinatesBuilder.createCoordinatesActionData()
    private val temperature = 60f
    private val gas = 450f
    private val firstId = 1L

    private lateinit var receiveAlert: ReceiveAlert
    private lateinit var alert: ReceiveAlertActionData
    private lateinit var alertRepository: AlertRepository

    @Mock
    private lateinit var alertNotificationService: AlertNotificationService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        alertRepository = InMemoryAlertRepository()
        receiveAlert = ReceiveAlert(alertRepository, alertNotificationService)
    }

    @Test
    fun receiveAlertShouldStoreAlert() {
        givenAlert()
        whenReceiveAlert()
        thenAlertIsStored()
    }

    @Test
    fun receiveAlertShouldSendItToClient() {
        givenAlert()
        whenReceiveAlert()
        thenSentAlertToClient()
    }

    private fun thenSentAlertToClient() {
        val invocations = 1
        Mockito.verify(alertNotificationService, times(invocations)).send(MockitoExtensions.anyObjectOf(Alert::class.java))
    }

    private fun thenAlertIsStored() {
        val storedAlert = alertRepository.find(firstId)
        Assert.assertEquals(storedAlert.id, firstId)
        Assert.assertEquals(storedAlert.detectionMethods, detectionMethods)
        Assert.assertEquals(storedAlert.temperature, temperature)
        Assert.assertEquals(storedAlert.gas, gas)
        assertCoordinates(storedAlert.coordinates, coordinatesActionData)
    }

    private fun assertCoordinates(coordinates: Coordinates, coordinatesActionData: CoordinatesActionData) {
        assertLatitude(coordinates.latitude, coordinatesActionData.latitudeActionData)
        assertLongitude(coordinates.longitude, coordinatesActionData.longitudeActionData)
    }

    private fun assertLongitude(longitude: Longitude, longitudeActionData: LongitudeActionData) {
        assertThat(longitudeActionData.degree).isEqualTo(longitude.degree)
        assertThat(longitudeActionData.minute).isEqualTo(longitude.minute)
        assertThat(longitudeActionData.second).isEqualTo(longitude.second)
        assertThat(longitudeActionData.cardinalPoint).isEqualTo(longitude.cardinalPoint)
    }

    private fun assertLatitude(latitude: Latitude, latitudeActionData: LatitudeActionData) {
        assertThat(latitudeActionData.degree).isEqualTo(latitude.degree)
        assertThat(latitudeActionData.minute).isEqualTo(latitude.minute)
        assertThat(latitudeActionData.second).isEqualTo(latitude.second)
        assertThat(latitudeActionData.cardinalPoint).isEqualTo(latitude.cardinalPoint)
    }

    private fun whenReceiveAlert() {
        receiveAlert(alert)
    }

    private fun givenAlert() {
        alert = ReceiveAlertActionData(coordinatesActionData, detectionMethods, temperature, gas)
    }
}