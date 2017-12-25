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

        checkCondition({ degree < zero }, { throw RuntimeException(degreeCanNotBeLowerThan0) })
        checkCondition({ degree >= maxLatitudeDegree }, { throw RuntimeException(degreeCanNotBeHigherThan90ForLatitude) })
        checkCondition({ minute < zero }, { throw RuntimeException(minuteCanNotLowerThanZero) })
        checkCondition({ minute >= maxMinuteAndSecond }, { throw RuntimeException(minuteCanNotHigherOrEqualsThanSixteen) })
        checkCondition({ second < zero }, { throw RuntimeException(secondCanNotBeLowerThanZero) })
        checkCondition({ second >= maxMinuteAndSecond }, { throw RuntimeException(secondCanNotBeHigherOrEqualsThanSixty) })
        checkCondition({ cardinalPoint == CardinalPoint.EAST }, { throw RuntimeException(latitudeCardinalPointCanNotBeEast) })
        checkCondition({ cardinalPoint == CardinalPoint.WEST }, { throw RuntimeException(latitudeCardinalPointCanNotBeWest) })
        return Coordinate(degree, minute, second, cardinalPoint)
    }

    private fun checkCondition(condition: () -> Boolean, exception: () -> RuntimeException) {
        if (condition()) exception()
    }
}