package com.untref.tesis.server.alert.infrastructure.persistence.file.entities

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.DetectionMethod
import java.io.Serializable

class AlertEntity(private val id: Long,
                  private val coordinatesEntity: CoordinatesEntity,
                  private val detectionMethods: List<DetectionMethod>,
                  private val temperature: Float,
                  private val gas: Float
) : Serializable {

    companion object {
        fun from(alert: Alert) = AlertEntity(alert.id, CoordinatesEntity.from(alert.coordinates), alert.detectionMethods, alert.temperature, alert.gas)
    }

    fun toAlert(): Alert = Alert(id, coordinatesEntity.toCoordinates(), detectionMethods, temperature, gas)
}