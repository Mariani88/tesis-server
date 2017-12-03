package com.untref.tesis.server.unit.action

import com.untref.tesis.server.action.ReceiveAlert
import com.untref.tesis.server.action.ReceiveAlertActionData
import com.untref.tesis.server.domain.*
import com.untref.tesis.server.extensions.MockitoExtensions
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import repository.InMemoryAlertRepository

class ReceiveAlertTest {

    private val latitude = "57°-23'-24''"
    private val longitude = "34°-45'-54''"
    private val detectionMethods = listOf(DetectionMethod.TEMPERATURE, DetectionMethod.FIRE)
    private val coordinates = Coordinates(latitude, longitude)
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
        val storedAlert = alertRepository.findById(firstId)
        Assert.assertEquals(storedAlert.id, firstId)
        Assert.assertEquals(storedAlert.coordinates, coordinates)
        Assert.assertEquals(storedAlert.detectionMethods, detectionMethods)
        Assert.assertEquals(storedAlert.temperature, temperature)
        Assert.assertEquals(storedAlert.gas, gas)
    }

    private fun whenReceiveAlert() {
        receiveAlert(alert)
    }

    private fun givenAlert() {
        alert = ReceiveAlertActionData(coordinates, detectionMethods, temperature, gas)
    }
}