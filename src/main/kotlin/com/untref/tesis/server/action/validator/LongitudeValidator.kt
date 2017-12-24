package com.untref.tesis.server.action.validator

import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.domain.Coordinate
import com.untref.tesis.server.resource.dto.CoordinateDto

class LongitudeValidator {
    fun validate(longitudeDto: CoordinateDto?): Coordinate {
        val longitude = utils.checkNotNull(longitudeDto, longitudeCanNotBeNull)
        val degree = utils.checkNotNull(longitude.degree, longitudeDegreeCanNotBeNull)
        val minute = utils.checkNotNull(longitude.minute, longitudeMinuteCanNotBeNull)
        val second = utils.checkNotNull(longitude.second, longitudeSecondCanNotNull)
        val cardinalPoint = utils.checkNotNull(longitude.cardinalPoint, longitudeCardinalPointCanNotBeNull)
        if (degree < zero) throw RuntimeException(longitudeDegreeCanNotLowerThanZero)
        if (degree >= maxLongitudeDegree) throw RuntimeException(longitudeDegreeCanNotHigherThanOneHundredEighteen)
        if (minute < zero) throw RuntimeException(longitudeMinuteCanNotLowerZero)
        if (minute >= maxMinuteAndSecond) throw RuntimeException(longitudeMinuteCanNotHigherOrEqualsThan60)
        if (second < zero) throw RuntimeException(longitudeSecondCanNotLowerThan0)
        if (second >= maxMinuteAndSecond) throw RuntimeException(longitudeSecondCanNotHigherOrEqualsThan60)
        if (cardinalPoint == CardinalPoint.NORTH) throw RuntimeException(longitudeCardinalPointCanNotBeNorth)
        if (cardinalPoint == CardinalPoint.SOUTH) throw RuntimeException(longitudeCardinalPointCanNotBeSouth)
        return Coordinate(degree, minute, second, cardinalPoint)
    }
}