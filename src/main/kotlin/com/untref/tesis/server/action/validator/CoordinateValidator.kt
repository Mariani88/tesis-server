package com.untref.tesis.server.action.validator

import com.untref.tesis.server.domain.Coordinate
import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto

class CoordinateValidator {

    companion object {
        fun validate(coordinatesDto: CoordinatesDto): Coordinates {
            val latitude = validateLatitude(coordinatesDto.latitude)
            val longitude = validateLongitude(coordinatesDto.longitude)
            return Coordinates(latitude, longitude)
        }

        private fun validateLongitude(longitudeDto: CoordinateDto?): Coordinate {
            val longitude = checkNotNull(longitudeDto, longitudeCanNotBeNull)
            return Coordinate(longitude.degree!!, longitude.minute!!, longitude.second!!, longitude.cardinalPoint!!)
        }

        private fun validateLatitude(latitudeDto: CoordinateDto?): Coordinate {
            val latitude = checkNotNull(latitudeDto, latitudeCanNotBeNull)
            val degree = checkNotNull(latitude.degree, degreeCanNotBeNull)
            val minute = checkNotNull(latitude.minute, minuteCanNotBeNull)
            val second = checkNotNull(latitude.second, secondCanNotBeNull)
            if (degree < 0) throw RuntimeException(degreeCanNotBeLowerThan0)
            if (degree > 90) throw RuntimeException(degreeCanNotBeHigherThan90ForLatitude)
            if (minute < 0) throw RuntimeException(minuteCanNotLowerThanZero)
            if (minute >= 60) throw RuntimeException(minuteCanNotHigherOrEqualsThanSixteen)


            return Coordinate(degree, minute, second, latitudeDto?.cardinalPoint!!)
        }


        private fun <T> checkNotNull(value: T?, errorMessage: String): T =
                value ?: throw RuntimeException(errorMessage)
    }
}