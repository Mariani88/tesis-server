package com.untref.tesis.server.alert.infrastructure.persistence.file.entities

import com.untref.tesis.server.alert.domain.Coordinates
import java.io.Serializable

class CoordinatesEntity(private val latitudeEntity: LatitudeEntity, private val longitudeEntity: LongitudeEntity) : Serializable {

    companion object {
        fun from(coordinates: Coordinates) = CoordinatesEntity(LatitudeEntity.from(coordinates.latitude), LongitudeEntity.from(coordinates
                .longitude))
    }

    fun toCoordinates(): Coordinates = Coordinates(latitudeEntity.toLatitude(), longitudeEntity.toLongitude())
}