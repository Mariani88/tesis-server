package com.untref.tesis.server.alert.infrastructure.persistence.file.entities

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.DetectionMethod
import java.io.Serializable
import java.util.*

class AlertEntity(private val id: Long,
                  private val coordinatesEntity: CoordinatesEntity,
                  private val detectionMethods: List<DetectionMethod>,
                  private val temperature: Float,
                  private val gas: Float,
                  private val date: Long

) : Serializable {

    companion object {
        fun from(alert: Alert) = AlertEntity(alert.id, CoordinatesEntity.from(alert.coordinates), alert.detectionMethods, alert.temperature, alert
                .gas, alert.date.time)
    }

    fun toAlert(): Alert = Alert(id, coordinatesEntity.toCoordinates(), detectionMethods, temperature, gas, Date(date))
}