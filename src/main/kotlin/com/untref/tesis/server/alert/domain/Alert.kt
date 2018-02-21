package com.untref.tesis.server.alert.domain

import java.util.*

data class Alert(val id: Long,
                 val coordinates: Coordinates,
                 val detectionMethods: List<DetectionMethod>,
                 val temperature: Float,
                 val gas: Float,
                 val date: Date
)