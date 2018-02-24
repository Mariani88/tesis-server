package com.untref.tesis.server.alert.action.validator

import com.untref.tesis.server.alert.action.CoordinatesActionData
import com.untref.tesis.server.resource.dto.CoordinatesDto

class CoordinateValidator(private val coordinateDtoPropertyValidator: CoordinateDtoPropertyValidator) {

    fun validate(coordinatesDto: CoordinatesDto): CoordinatesActionData {
        val latitude = coordinateDtoPropertyValidator.validateLatitudeDto(coordinatesDto.latitude)
        val longitude = coordinateDtoPropertyValidator.validateLongitudeDto(coordinatesDto.longitude)
        return CoordinatesActionData(latitude, longitude)
    }
}