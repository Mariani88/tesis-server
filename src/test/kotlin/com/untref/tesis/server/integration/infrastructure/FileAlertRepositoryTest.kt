package com.untref.tesis.server.integration.infrastructure

import com.untref.tesis.server.builders.CoordinatesBuilder
import com.untref.tesis.server.domain.*
import com.untref.tesis.server.infrastructure.persistence.file.FileAlertRepository
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
    private val coordinates = CoordinatesBuilder.createCoordinates()
    private val detectionMethods = listOf(DetectionMethod.FIRE)
    private val temperature = 40f
    private val gas = 430f
    private val path = "alertsTest.dat"

    @Before
    fun setUp() {
        fileAlertRepository = FileAlertRepository(path)
    }

    @After
    fun clean(){
        File(path).delete()
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