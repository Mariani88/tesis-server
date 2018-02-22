package com.untref.tesis.server.alert.infrastructure.persistence.file.entities

import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Latitude
import java.io.Serializable

class LatitudeEntity(private val degree: Int, private val minute: Int, private val second: Double, private val cardinalPoint: CardinalPoint):
        Serializable {

    companion object {
        fun from(latitude: Latitude) = LatitudeEntity(latitude.degree, latitude.minute, latitude.second, latitude.cardinalPoint)
    }

    fun toLatitude(): Latitude = Latitude.build(degree, minute, second, cardinalPoint)
}