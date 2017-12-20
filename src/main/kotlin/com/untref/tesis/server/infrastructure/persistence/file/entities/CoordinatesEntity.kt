package com.untref.tesis.server.infrastructure.persistence.file.entities

import com.untref.tesis.server.domain.Coordinates
import java.io.Serializable

class CoordinatesEntity(private val latitudeEntity: CoordinateEntity, private val longitudeEntity: CoordinateEntity) : Serializable {

    companion object {
        fun from(coordinates: Coordinates) = CoordinatesEntity(CoordinateEntity.from(coordinates.latitude), CoordinateEntity.from(coordinates
                .longitude))
    }

    fun toCoordinates(): Coordinates = Coordinates(latitudeEntity.toCoordinate(), longitudeEntity.toCoordinate())
}