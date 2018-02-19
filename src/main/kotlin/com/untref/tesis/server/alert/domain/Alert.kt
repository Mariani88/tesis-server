package com.untref.tesis.server.alert.domain

data class Alert(val id: Long,
                 val coordinates: Coordinates,
                 val detectionMethods: List<DetectionMethod>,
                 val temperature: Float,
                 val gas: Float
)