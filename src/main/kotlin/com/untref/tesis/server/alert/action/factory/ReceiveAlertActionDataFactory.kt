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
        val detectionMethods = validate(fireAlert.detectionMethods)
        val temperature = checkNotNull(fireAlert.temperature, temperatureCanNotBeNull)
        val gas = validate(fireAlert.gas)
        return ReceiveAlertActionData(coordinates, detectionMethods, temperature, gas)
    }

    private fun validate(coordinatesDto: CoordinatesDto?): CoordinatesActionData =
            checkNotNull(coordinatesDto, coordinatesCanNotBeNull).let { coordinateValidator.validate(it) }

    private fun validate(gas: Float?): Float =
            checkNotNull(gas, gasCanNotBeNull).let { checkNotNegative(it, gasCanNotBeNegative) }

    private fun checkNotNegative(value: Float, errorMessage: String): Float =
            if (value >= 0) value else throw RuntimeException(errorMessage)

    private fun validate(detectionMethods: List<DetectionMethod>?): List<DetectionMethod> =
            checkNotNull(detectionMethods, detectionMethodsCanNotBeNull).let { checkNotEmpty(it) }

    private fun checkNotEmpty(detectionMethods: List<DetectionMethod>): List<DetectionMethod> =
            if (detectionMethods.isNotEmpty()) detectionMethods else throw RuntimeException(detectionMethodsCanNotBeEmpty)
}