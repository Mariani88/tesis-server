package com.untref.tesis.server.alert.infrastructure.persistence.file.entities

import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Longitude
import java.io.Serializable

class LongitudeEntity(private val degree: Int, private val minute: Int, private val second: Double, private val cardinalPoint: CardinalPoint) :
        Serializable {

    companion object {
        fun from(longitude: Longitude) = LongitudeEntity(longitude.degree, longitude.minute, longitude.second, longitude.cardinalPoint)
    }

    fun toLongitude(): Longitude = Longitude.build(degree, minute, second, cardinalPoint)
}