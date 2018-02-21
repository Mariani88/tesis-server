package com.untref.tesis.server.builders

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.builders.CoordinatesBuilder.Companion.createCoordinates
import java.util.*

class AlertBuilder {

    companion object {
        private const val alertId = 1L
        private val date = Date()

        fun createAlert(): Alert =
                Alert(alertId, createCoordinates(),
                        defaultDetectionMethods, defaultTemperature, defaultGas, date)
    }
}
