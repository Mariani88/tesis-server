package com.untref.tesis.server.alert.action.validator

import com.untref.tesis.server.alert.action.LongitudeActionData
import com.untref.tesis.server.resource.dto.CoordinateDto

class LongitudeValidator {
    fun validate(longitudeDto: CoordinateDto?): LongitudeActionData {
        val longitude = utils.checkNotNull(longitudeDto, longitudeCanNotBeNull)
        val degree = utils.checkNotNull(longitude.degree, longitudeDegreeCanNotBeNull)
        val minute = utils.checkNotNull(longitude.minute, longitudeMinuteCanNotBeNull)
        val second = utils.checkNotNull(longitude.second, longitudeSecondCanNotNull)
        val cardinalPoint = utils.checkNotNull(longitude.cardinalPoint, longitudeCardinalPointCanNotBeNull)

        return LongitudeActionData(degree, minute, second, cardinalPoint)
    }
}