package com.untref.tesis.server.alert.action.validator

import com.untref.tesis.server.alert.action.LatitudeActionData
import com.untref.tesis.server.alert.action.LongitudeActionData
import com.untref.tesis.server.resource.dto.CoordinateDto
import utils.checkNotNull

class CoordinateDtoPropertyValidator {

    fun validateLatitudeDto(latitudeDto: CoordinateDto?): LatitudeActionData {
        val latitude = checkNotNull(latitudeDto, latitudeCanNotBeNull)
        val degree = checkNotNull(latitude.degree, degreeCanNotBeNull)
        val minute = checkNotNull(latitude.minute, minuteCanNotBeNull)
        val second = checkNotNull(latitude.second, secondCanNotBeNull)
        val cardinalPoint = checkNotNull(latitude.cardinalPoint, latitudeCardinalPointCanNotBeNull)
        return LatitudeActionData(degree, minute, second, cardinalPoint)
    }


    fun validateLongitudeDto(longitudeDto: CoordinateDto?): LongitudeActionData {
        val longitude = utils.checkNotNull(longitudeDto, longitudeCanNotBeNull)
        val degree = utils.checkNotNull(longitude.degree, longitudeDegreeCanNotBeNull)
        val minute = utils.checkNotNull(longitude.minute, longitudeMinuteCanNotBeNull)
        val second = utils.checkNotNull(longitude.second, longitudeSecondCanNotNull)
        val cardinalPoint = utils.checkNotNull(longitude.cardinalPoint, longitudeCardinalPointCanNotBeNull)

        return LongitudeActionData(degree, minute, second, cardinalPoint)
    }
}