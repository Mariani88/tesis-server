package com.untref.tesis.server.alert.domain

import java.io.Serializable

enum class DetectionMethod: Serializable {

    SMOKE, TEMPERATURE, FIRE, TEMPERATURE_INCREMENT
}