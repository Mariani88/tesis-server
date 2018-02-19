package com.untref.tesis.server.builders

import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Coordinate
import com.untref.tesis.server.alert.domain.Coordinates

class CoordinatesBuilder {

    companion object {

        private val defaultDegree = 57
        private val defaultMinutes = 34
        private val defaultSeconds = 23.1f
        private val defaultLongitudeCardinalPoint = CardinalPoint.EAST
        private val defaultLatitudeCardinalPoint = CardinalPoint.SOUTH

        fun createCoordinates() = Coordinates(createLatitude(), createLongitude())

        private fun createLongitude(degree: Int = defaultDegree, minutes: Int = defaultMinutes, seconds: Float = defaultSeconds, cardinalPoint:
            CardinalPoint = defaultLongitudeCardinalPoint) = Coordinate(degree, minutes, seconds, cardinalPoint)

        private fun createLatitude(degree: Int = defaultDegree, minutes: Int = defaultMinutes, seconds: Float = defaultSeconds, cardinalPoint:
        CardinalPoint = defaultLatitudeCardinalPoint) = Coordinate(degree, minutes, seconds, cardinalPoint)
    }
}