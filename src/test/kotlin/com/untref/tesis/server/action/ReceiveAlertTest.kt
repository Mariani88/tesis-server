package com.untref.tesis.server.action

import com.untref.tesis.server.domain.Alert

import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.domain.DetectionMethod
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
class ReceiveAlertTest {

    private val latitude = "57°-23'-24''"
    private val longitude = "34°-45'-54''"
    private val detectionMethods = listOf(DetectionMethod.TEMPERATURE, DetectionMethod.FIRE)
    private val coordinates = Coordinates(latitude, longitude)
    private val temperature = 60f
    private val gas = 450f

    lateinit var receiveAlert: ReceiveAlert
    lateinit var alert: Alert

   /* @Before
    fun setUp() {
        receiveAlert = ReceiveAlert()
    }

    @Test
    fun receiveAlertShouldStoreAlert() {
        givenAlert()
        whenReceiveAlert()
        thenAlertIsStored()


        fail()
    }

    private fun thenAlertIsStored() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    private fun whenReceiveAlert() {
        receiveAlert(alert)
    }


    private fun givenAlert() {
        alert = Alert(coordinates, detectionMethods, temperature, gas)
    }

    @Test
    fun receiveAlertShouldSendItToClient() {
        fail()
    }*/
}