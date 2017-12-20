package com.untref.tesis.server.infrastructure.persistence.file.entities

import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.domain.Coordinate
import java.io.Serializable

class CoordinateEntity(private val degree: Int, private val minute: Int, private val second: Float, private val cardinalPoint: CardinalPoint) : Serializable {

    companion object {
        fun from(coordinate: Coordinate) = CoordinateEntity(coordinate.degree, coordinate.minute, coordinate.second, coordinate.cardinalPoint)
    }

    fun toCoordinate(): Coordinate = Coordinate(degree, minute, second, cardinalPoint)
}