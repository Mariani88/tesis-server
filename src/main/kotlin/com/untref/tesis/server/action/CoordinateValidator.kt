package com.untref.tesis.server.action

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

        private fun validateLongitude(longitude: CoordinateDto?): Coordinate {
            return Coordinate(longitude!!.degree!!, longitude!!.minute!!, longitude!!.second!!, longitude!!.cardinalPoint!!)
        }

        private fun validateLatitude(latitudeDto: CoordinateDto?): Coordinate {
            val latitude = checkNotNull(latitudeDto, "Degree can not null")
            val degree = checkNotNull(latitude.degree, "Degree can not null")
            if(degree < 0) throw RuntimeException("Degree can not lower than 0")
            return Coordinate(degree, latitudeDto!!.minute!!, latitudeDto!!.second!!, latitudeDto!!.cardinalPoint!!)
        }


        private fun <T> checkNotNull(value: T?, errorMessage: String): T =
                value ?: throw RuntimeException(errorMessage)
    }
}