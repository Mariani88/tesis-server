package com.untref.tesis.server.action

import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto
import java.util.*

class ReceiveAlertActionDataFactory {

    companion object {
        /*fun create(fireAlertDto: FireAlertDto): ReceiveAlertActionData {
            checkNotNull(fireAlertDto)
            val coordinates = validate(fireAlertDto.coordinates)
            val detectionMethods = validate(fireAlertDto.detectionMethods)
            val temperature = validateNotNull(fireAlertDto.temperature)
            val gas = validate(fireAlertDto.gas)
            return ReceiveAlertActionData(coordinates, detectionMethods, temperature, gas)
        }

        private fun validateNotNull(temperature: Float?): Float = temperature.toOptional().orElseThrow {
            RuntimeException("temperature can not be null")
        }

        private fun checkNotNull(fireAlertDto: FireAlertDto): FireAlertDto =
                fireAlertDto.toOptional().orElseThrow { RuntimeException("fire alert Dto can not be null") }

        private fun validate(coordinatesDto: CoordinatesDto?): Coordinates =
                coordinatesDto.toOptional().map { CoordinateValidator.validate(it) }.orElseThrow { RuntimeException("coordinates can not be null") }

        private fun validate(gas: Float?): Float =
                gas.toOptional().map { checkPositive(it) }.orElseThrow { RuntimeException("gas can not be null") }

        private fun checkPositive(value: Float): Float =
                if (value > 0) value else throw RuntimeException("value is not positive")

        private fun validate(detectionMethods: List<DetectionMethod>?): List<DetectionMethod> =
                detectionMethods.toOptional().map { checkNotEmpty(it) }.orElseThrow { RuntimeException("Detection methods can not be null") }

        private fun checkNotEmpty(detectionMethods: List<DetectionMethod>): List<DetectionMethod> =
                if (detectionMethods.isNotEmpty()) detectionMethods else throw RuntimeException("detectionMethod can not be empty")
    */
    }
}

private fun <T> T?.toOptional() = Optional.ofNullable(this!!)