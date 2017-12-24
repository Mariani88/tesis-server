package com.untref.tesis.server.action.validator

import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.domain.Coordinate
import com.untref.tesis.server.resource.dto.CoordinateDto
import utils.checkNotNull

class LatitudeValidator {

    fun validate(latitudeDto: CoordinateDto?): Coordinate {
        val latitude = checkNotNull(latitudeDto, latitudeCanNotBeNull)
        val degree = checkNotNull(latitude.degree, degreeCanNotBeNull)
        val minute = checkNotNull(latitude.minute, minuteCanNotBeNull)
        val second = checkNotNull(latitude.second, secondCanNotBeNull)
        val cardinalPoint = checkNotNull(latitude.cardinalPoint, latitudeCardinalPointCanNotBeNull)
        if (degree < zero) throw RuntimeException(degreeCanNotBeLowerThan0)
        if (degree >= maxLatitudeDegree) throw RuntimeException(degreeCanNotBeHigherThan90ForLatitude)
        if (minute < zero) throw RuntimeException(minuteCanNotLowerThanZero)
        if (minute >= maxMinuteAndSecond) throw RuntimeException(minuteCanNotHigherOrEqualsThanSixteen)
        if (second < zero) throw RuntimeException(secondCanNotBeLowerThanZero)
        if (second >= maxMinuteAndSecond) throw RuntimeException(secondCanNotBeHigherOrEqualsThanSixty)
        if (cardinalPoint == CardinalPoint.EAST) throw RuntimeException(latitudeCardinalPointCanNotBeEast)
        if (cardinalPoint == CardinalPoint.WEST) throw RuntimeException(latitudeCardinalPointCanNotBeWest)
        return Coordinate(degree, minute, second, cardinalPoint)
    }
}