package com.untref.tesis.server.alert.action.factory

import com.untref.tesis.server.alert.action.CoordinatesActionData
import com.untref.tesis.server.alert.action.ReceiveAlertActionData
import com.untref.tesis.server.alert.action.validator.CoordinateValidator
import com.untref.tesis.server.alert.domain.Coordinates
import com.untref.tesis.server.alert.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto
import utils.checkNotNull

class ReceiveAlertActionDataFactory(private val coordinateValidator: CoordinateValidator) {

    fun create(fireAlertDto: FireAlertDto?): ReceiveAlertActionData {
        val fireAlert = checkNotNull(fireAlertDto, fireAlertDtoCanNotBeNull)
        val coordinates = validate(fireAlert.coordinates)
        val detectionMethods = checkNotNull(fireAlert.detectionMethods, detectionMethodsCanNotBeNull)
        val temperature = checkNotNull(fireAlert.temperature, temperatureCanNotBeNull)
        val gas = checkNotNull(fireAlert.gas, gasCanNotBeNull)
        return ReceiveAlertActionData(coordinates, detectionMethods, temperature, gas)
    }

    private fun validate(coordinatesDto: CoordinatesDto?): CoordinatesActionData =
            checkNotNull(coordinatesDto, coordinatesCanNotBeNull).let { coordinateValidator.validate(it) }
}