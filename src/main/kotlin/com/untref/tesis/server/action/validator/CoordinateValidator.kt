package com.untref.tesis.server.action.validator

import com.untref.tesis.server.domain.Coordinate
import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import utils.checkNotNull

class CoordinateValidator {


    companion object {
        private val latitudeValidator = LatitudeValidator()

        fun validate(coordinatesDto: CoordinatesDto): Coordinates {
            val latitude = latitudeValidator.validate(coordinatesDto.latitude)
            val longitude = validateLongitude(coordinatesDto.longitude)
            return Coordinates(latitude, longitude)
        }

        private fun validateLongitude(longitudeDto: CoordinateDto?): Coordinate {
            val longitude = checkNotNull(longitudeDto, longitudeCanNotBeNull)
            val degree = checkNotNull(longitude.degree, longitudeDegreeCanNotBeNull)
            if(degree < 0) throw RuntimeException(longitudeDegreeCanNotLowerThanZero)
            if(degree>=180) throw RuntimeException(longitudeDegreeCanNotHigherThanOneHundredEighteen)
            return Coordinate(longitude.degree!!, longitude.minute!!, longitude.second!!, longitude.cardinalPoint!!)
        }
    }
}