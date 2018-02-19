package com.untref.tesis.server.alert.action.validator

import com.untref.tesis.server.alert.domain.Coordinates
import com.untref.tesis.server.resource.dto.CoordinatesDto

class CoordinateValidator(private val latitudeValidator: LatitudeValidator, private val longitudeValidator: LongitudeValidator) {

    fun validate(coordinatesDto: CoordinatesDto): Coordinates {
        val latitude = latitudeValidator.validate(coordinatesDto.latitude)
        val longitude = longitudeValidator.validate(coordinatesDto.longitude)
        return Coordinates(latitude, longitude)
    }
}