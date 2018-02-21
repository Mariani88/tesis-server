package com.untref.tesis.server.builders

import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Coordinate
import com.untref.tesis.server.alert.domain.Coordinates

class CoordinatesBuilder {

    companion object {

        private const val defaultDegree = 57
        private const val defaultMinutes = 34
        private const val defaultSeconds = 23.1
        private val defaultLongitudeCardinalPoint = CardinalPoint.EAST
        private val defaultLatitudeCardinalPoint = CardinalPoint.SOUTH

        fun createCoordinates() = Coordinates(createLatitude(), createLongitude())

        private fun createLongitude(degree: Int = defaultDegree, minutes: Int = defaultMinutes, seconds: Double = defaultSeconds, cardinalPoint:
            CardinalPoint = defaultLongitudeCardinalPoint) = Coordinate(degree, minutes, seconds, cardinalPoint)

        private fun createLatitude(degree: Int = defaultDegree, minutes: Int = defaultMinutes, seconds: Double = defaultSeconds, cardinalPoint:
        CardinalPoint = defaultLatitudeCardinalPoint) = Coordinate(degree, minutes, seconds, cardinalPoint)
    }
}