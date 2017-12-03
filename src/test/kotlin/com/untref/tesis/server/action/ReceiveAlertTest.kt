package com.untref.tesis.server.action

import com.untref.tesis.server.domain.AlertRepository

import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.domain.DetectionMethod
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import repository.InMemoryAlertRepository

class ReceiveAlertTest {

    private val latitude = "57°-23'-24''"
    private val longitude = "34°-45'-54''"
    private val detectionMethods = listOf(DetectionMethod.TEMPERATURE, DetectionMethod.FIRE)
    private val coordinates = Coordinates(latitude, longitude)
    private val temperature = 60f
    private val gas = 450f
    private val firstId = 1L

    lateinit var receiveAlert: ReceiveAlert
    lateinit var alert: ReceiveAlertActionData
    lateinit var alertRepository: AlertRepository

   @Before
    fun setUp() {
       alertRepository = InMemoryAlertRepository()
        receiveAlert = ReceiveAlert(alertRepository)
    }

    @Test
    fun receiveAlertShouldStoreAlert() {
        givenAlert()
        whenReceiveAlert()
        thenAlertIsStored()
    }

    /*@Test
    fun receiveAlertShouldSendItToClient() {
        fail()
    }*/

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