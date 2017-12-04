package com.untref.tesis.server.integration

import com.untref.tesis.server.domain.Alert
import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.domain.DetectionMethod
import com.untref.tesis.server.infrastructure.persistence.FileAlertRepository
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class FileAlertRepositoryTest {

    private lateinit var fileAlertRepository: FileAlertRepository
    private lateinit var alert: Alert
    private val alertId = 1L
    private lateinit var storedAlert: Alert
    private val longitude = "44°-45'-21''"
    private val latitude = "32°-34'-45''"
    private val coordinates = Coordinates(latitude, longitude)
    private val detectionMethods = listOf(DetectionMethod.FIRE)
    private val temperature = 40f
    private val gas = 430f

    @Before
    fun setUp() {
        fileAlertRepository = FileAlertRepository()
    }

    @After
    fun clean(){
        File("alerts.dat").delete()
    }

    @Test
    fun storeAlertShouldStoreIt() {
        givenAlert()
        whenStoreAlert()
        thenStoreIt()
    }

    private fun thenStoreIt() {
        storedAlert = fileAlertRepository.findById(alertId)
        Assert.assertEquals(alert, storedAlert)
        Assert.assertEquals(alertId + 1, fileAlertRepository.lastId())
    }

    private fun whenStoreAlert() {
        fileAlertRepository.storeAlert(alert)
    }

    private fun givenAlert() {
        alert = Alert(alertId, coordinates, detectionMethods, temperature, gas)
    }
}