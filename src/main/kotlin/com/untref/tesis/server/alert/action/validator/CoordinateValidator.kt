package com.untref.tesis.server.alert.action.validator

import com.untref.tesis.server.alert.action.CoordinatesActionData
import com.untref.tesis.server.alert.domain.Coordinates
import com.untref.tesis.server.resource.dto.CoordinatesDto

class CoordinateValidator(private val latitudeValidator: LatitudeValidator, private val longitudeValidator: LongitudeValidator) {

    fun validate(coordinatesDto: CoordinatesDto): CoordinatesActionData {
        val latitude = latitudeValidator.validate(coordinatesDto.latitude)
        val longitude = longitudeValidator.validate(coordinatesDto.longitude)
        return CoordinatesActionData(latitude, longitude)
    }
}