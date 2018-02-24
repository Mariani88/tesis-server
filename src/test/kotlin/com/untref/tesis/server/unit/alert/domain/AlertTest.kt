package com.untref.tesis.server.unit.alert.domain

import com.untref.tesis.server.alert.action.factory.detectionMethodsCanNotBeEmpty

import com.untref.tesis.server.alert.action.factory.gasCanNotBeNegative
import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.DetectionMethod
import com.untref.tesis.server.builders.CoordinatesBuilder.Companion.createCoordinates
import com.untref.tesis.server.builders.defaultDetectionMethods
import com.untref.tesis.server.builders.defaultGas
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test
import java.util.*

class AlertTest {

    private var detectionMethods: List<DetectionMethod>? = null
    private var gas: Float? = null
    private var exception: Throwable? = null
    private var alert: Alert? = null
    private val id = 1L
    private val coordinates = createCoordinates()
    private val temperature = 23.0f
    private val date = Date()

    @Test
    fun negativeGasThrowException() {
        givenAGas(-1f)
        givenDetectionMethods(defaultDetectionMethods)

        whenCreateAlert()

        thenExpectedException(gasCanNotBeNegative)
    }

    @Test
    fun emptyDetectionMethodThrowException() {
        givenAGas(defaultGas)
        givenDetectionMethods(listOf())

        whenCreateAlert()

        thenExpectedException(detectionMethodsCanNotBeEmpty)
    }

    @Test
    fun validParametersCreateCorrectly() {
        givenAGas(defaultGas)
        givenDetectionMethods(defaultDetectionMethods)

        whenCreateAlert()

        thenExpectedAlert()
    }

    private fun thenExpectedAlert() {
        assertThat(exception).isNull()
        assertThat(alert).isNotNull()
        assertThat(alert!!.coordinates).isEqualTo(coordinates)
        assertThat(alert!!.id).isEqualTo(id)
        assertThat(alert!!.date).isEqualTo(date)
        assertThat(alert!!.detectionMethods).isEqualTo(defaultDetectionMethods)
        assertThat(alert!!.gas).isEqualTo(defaultGas)
        assertThat(alert!!.temperature).isEqualTo(temperature)
    }

    private fun thenExpectedException(message: String) {
        assertThat(exception).isNotNull()
        assertThat(exception?.message).isEqualTo(message)
    }

    private fun whenCreateAlert() {
        exception = catchThrowable { alert = Alert.build(id, coordinates, detectionMethods!!,
            temperature, gas!!, date) }
    }

    private fun givenAGas(gas: Float) {
        this.gas = gas
    }

    private fun givenDetectionMethods(detectionMethods: List<DetectionMethod>) {
        this.detectionMethods = detectionMethods
    }
}