package com.untref.tesis.server.action.validator

import com.untref.tesis.server.domain.Coordinate
import com.untref.tesis.server.resource.dto.CoordinateDto
import utils.checkNotNull

class LatitudeValidator {

    fun validate(latitudeDto: CoordinateDto?): Coordinate {
        val latitude = checkNotNull(latitudeDto, latitudeCanNotBeNull)
        val degree = checkNotNull(latitude.degree, degreeCanNotBeNull)
        val minute = checkNotNull(latitude.minute, minuteCanNotBeNull)
        val second = checkNotNull(latitude.second, secondCanNotBeNull)
        if (degree < 0) throw RuntimeException(degreeCanNotBeLowerThan0)
        if (degree > 90) throw RuntimeException(degreeCanNotBeHigherThan90ForLatitude)
        if (minute < 0) throw RuntimeException(minuteCanNotLowerThanZero)
        if (minute >= 60) throw RuntimeException(minuteCanNotHigherOrEqualsThanSixteen)
        if (second < 0) throw RuntimeException(secondCanNotBeLowerThanZero)
        if (second >= 60) throw RuntimeException(secondCanNotBeHigherOrEqualsThanSixty)

        return Coordinate(degree, minute, second, latitudeDto?.cardinalPoint!!)
    }
}