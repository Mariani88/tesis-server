package com.untref.tesis.server.alert.domain

import com.untref.tesis.server.alert.action.validator.*
import utils.checkCondition

data class Latitude private constructor(val degree: Int, val minute: Int, val second: Double, val cardinalPoint: CardinalPoint) {

    companion object {

        fun build(degree: Int, minute: Int, second: Double, cardinalPoint: CardinalPoint): Latitude {
            checkCondition({ degree < zero }, { throw RuntimeException(degreeCanNotBeLowerThan0) })
            checkCondition({ degree >= maxLatitudeDegree }, { throw RuntimeException(degreeCanNotBeHigherThan90ForLatitude) })
            checkCondition({ minute < zero }, { throw RuntimeException(minuteCanNotLowerThanZero) })
            checkCondition({ minute >= maxMinuteAndSecond }, { throw RuntimeException(minuteCanNotHigherOrEqualsThanSixteen) })
            checkCondition({ second < zero }, { throw RuntimeException(secondCanNotBeLowerThanZero) })
            checkCondition({ second >= maxMinuteAndSecond }, { throw RuntimeException(secondCanNotBeHigherOrEqualsThanSixty) })
            checkCondition({ cardinalPoint == CardinalPoint.EAST }, { throw RuntimeException(latitudeCardinalPointCanNotBeEast) })
            checkCondition({ cardinalPoint == CardinalPoint.WEST }, { throw RuntimeException(latitudeCardinalPointCanNotBeWest) })
            return Latitude(degree, minute, second, cardinalPoint)
        }
    }
}