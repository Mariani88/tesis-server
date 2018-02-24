package com.untref.tesis.server.alert.domain

import com.untref.tesis.server.alert.action.factory.detectionMethodsCanNotBeEmpty
import com.untref.tesis.server.alert.action.factory.gasCanNotBeNegative
import java.util.*

data class Alert private constructor(val id: Long, val coordinates: Coordinates, val detectionMethods: List<DetectionMethod>,
                                     val temperature: Float, val gas: Float, val date: Date) {

    companion object {

        fun build(id: Long, coordinates: Coordinates, detectionMethods: List<DetectionMethod>,
                  temperature: Float, gas: Float, date: Date): Alert {
            checkNotEmpty(detectionMethods)
            checkNotNegative(gas, gasCanNotBeNegative)

            return Alert(id, coordinates, detectionMethods, temperature, gas, date)
        }

        private fun checkNotEmpty(detectionMethods: List<DetectionMethod>): List<DetectionMethod> =
                if (detectionMethods.isNotEmpty()) detectionMethods else throw RuntimeException(detectionMethodsCanNotBeEmpty)

        private fun checkNotNegative(value: Float, errorMessage: String): Float =
                if (value >= 0) value else throw RuntimeException(errorMessage)
    }
}