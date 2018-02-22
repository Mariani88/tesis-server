package com.untref.tesis.server.builders

import com.untref.tesis.server.alert.action.CoordinatesActionData
import com.untref.tesis.server.alert.action.LatitudeActionData
import com.untref.tesis.server.alert.action.LongitudeActionData
import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Coordinates
import com.untref.tesis.server.alert.domain.Latitude
import com.untref.tesis.server.alert.domain.Longitude

class CoordinatesBuilder {

    companion object {

        private const val defaultDegree = 57
        private const val defaultMinutes = 34
        private const val defaultSeconds = 23.1
        private val defaultLongitudeCardinalPoint = CardinalPoint.EAST
        private val defaultLatitudeCardinalPoint = CardinalPoint.SOUTH

        fun createCoordinatesActionData() = CoordinatesActionData(createLatitudeActionData(), createLongitudeActionData())

        fun createCoordinates(): Coordinates = Coordinates(createLatitude(), createLongitude())

        private fun createLongitude(): Longitude = Longitude.build(defaultDegree, defaultMinutes, defaultSeconds, defaultLongitudeCardinalPoint)

        private fun createLatitude(): Latitude = Latitude.build(defaultDegree, defaultMinutes, defaultSeconds, defaultLatitudeCardinalPoint)

        private fun createLongitudeActionData(degree: Int = defaultDegree, minutes: Int = defaultMinutes, seconds: Double = defaultSeconds, cardinalPoint:
        CardinalPoint = defaultLongitudeCardinalPoint) = LongitudeActionData(degree, minutes, seconds, cardinalPoint)

        private fun createLatitudeActionData(degree: Int = defaultDegree, minutes: Int = defaultMinutes, seconds: Double = defaultSeconds, cardinalPoint:
        CardinalPoint = defaultLatitudeCardinalPoint) = LatitudeActionData(degree, minutes, seconds, cardinalPoint)
    }
}