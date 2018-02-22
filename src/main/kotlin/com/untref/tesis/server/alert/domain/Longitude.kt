package com.untref.tesis.server.alert.domain

import com.untref.tesis.server.alert.action.validator.*
import utils.checkCondition

data class Longitude private constructor(val degree: Int, val minute: Int, val second: Double, val cardinalPoint: CardinalPoint) {

    companion object {

        fun build(degree: Int, minute: Int, second: Double, cardinalPoint: CardinalPoint): Longitude {
            checkCondition({ degree < zero }, { throw RuntimeException(longitudeDegreeCanNotLowerThanZero) })
            checkCondition({ degree >= maxLongitudeDegree }, { throw RuntimeException(longitudeDegreeCanNotHigherThanOneHundredEighteen) })
            checkCondition({ minute < zero }, { throw RuntimeException(longitudeMinuteCanNotLowerZero) })
            checkCondition({ minute >= maxMinuteAndSecond }, { throw RuntimeException(longitudeMinuteCanNotHigherOrEqualsThan60) })
            checkCondition({ second < zero }, { throw RuntimeException(longitudeSecondCanNotLowerThan0) })
            checkCondition({ second >= maxMinuteAndSecond }, { throw RuntimeException(longitudeSecondCanNotHigherOrEqualsThan60) })
            checkCondition({ cardinalPoint == CardinalPoint.NORTH }, { throw RuntimeException(longitudeCardinalPointCanNotBeNorth) })
            checkCondition({ cardinalPoint == CardinalPoint.SOUTH }, { throw RuntimeException(longitudeCardinalPointCanNotBeSouth) })
            return Longitude(degree, minute, second, cardinalPoint)
        }
    }
}