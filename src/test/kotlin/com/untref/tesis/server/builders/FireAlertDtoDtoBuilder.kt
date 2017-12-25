package com.untref.tesis.server.builders

import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.domain.DetectionMethod
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import com.untref.tesis.server.resource.dto.FireAlertDto

val defaultDegree = 30
val defaultMinute = 50
val defaultSecond = 23.4f
val south = CardinalPoint.SOUTH
val east = CardinalPoint.EAST
val defaultTemperature = 30f
val defaultGas = 400f
val defaultDetectionMethods = listOf(DetectionMethod.FIRE)

fun createLatitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                   second: Float? = defaultSecond, cardinalPoint: CardinalPoint? = south) =
        CoordinateDto(degree, minute, second, cardinalPoint)

fun createLongitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                    second: Float? = defaultSecond, cardinalPoint: CardinalPoint? = east) =
        CoordinateDto(degree, minute, second, cardinalPoint)

fun createCoordinates() = CoordinatesDto(createLatitude(), createLongitude())

fun createFireAlertDto(coordinates: CoordinatesDto? = createCoordinates(), detectionMethods: List<DetectionMethod>? = defaultDetectionMethods,
                       temperature: Float? = defaultTemperature, gas: Float? = defaultGas) =
        FireAlertDto(coordinates, detectionMethods, temperature, gas)