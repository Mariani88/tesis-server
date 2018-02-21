package com.untref.tesis.server.builders

import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto

const val defaultDegree = 30
const val defaultMinute = 50
const val defaultSecond = 23.4
val south = CardinalPoint.SOUTH
val east = CardinalPoint.EAST
const val defaultTemperature = 30f
const val defaultGas = 400f
val defaultDetectionMethods = listOf(DetectionMethod.FIRE)

fun createLatitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                   second: Double? = defaultSecond, cardinalPoint: CardinalPoint? = south) =
        CoordinateDto(degree, minute, second, cardinalPoint)

fun createLongitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                    second: Double? = defaultSecond, cardinalPoint: CardinalPoint? = east) =
        CoordinateDto(degree, minute, second, cardinalPoint)

fun createCoordinatesDto() = CoordinatesDto(createLatitude(), createLongitude())

fun createFireAlertDto(coordinates: CoordinatesDto? = createCoordinatesDto(), detectionMethods: List<DetectionMethod>? = defaultDetectionMethods,
                       temperature: Float? = defaultTemperature, gas: Float? = defaultGas) =
        FireAlertDto(coordinates, detectionMethods, temperature, gas)