package com.untref.tesis.server.alert.action.validator

import com.untref.tesis.server.alert.action.LatitudeActionData
import com.untref.tesis.server.resource.dto.CoordinateDto
import utils.checkNotNull

class LatitudeValidator {

    fun validate(latitudeDto: CoordinateDto?): LatitudeActionData {
        val latitude = checkNotNull(latitudeDto, latitudeCanNotBeNull)
        val degree = checkNotNull(latitude.degree, degreeCanNotBeNull)
        val minute = checkNotNull(latitude.minute, minuteCanNotBeNull)
        val second = checkNotNull(latitude.second, secondCanNotBeNull)
        val cardinalPoint = checkNotNull(latitude.cardinalPoint, latitudeCardinalPointCanNotBeNull)
        return LatitudeActionData(degree, minute, second, cardinalPoint)
    }
}