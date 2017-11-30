package com.untref.tesis.server.domain

data class Alert(val coordinates: Coordinates,
                 val detectionMethods: List<DetectionMethod>,
                 val temperature: Float,
                 val gas: Float
                )