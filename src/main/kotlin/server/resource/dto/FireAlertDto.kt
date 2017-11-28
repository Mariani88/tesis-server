package server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty
import server.domain.DetectionMethod

data class FireAlertDto(
        @JsonProperty("coor")
        val coordinates: CoordinatesDto?,

        @JsonProperty("det")
        val detectionMethods: List<DetectionMethod>?,

        @JsonProperty("temp")
        val temperature: Float?,

        @JsonProperty("gas")
        val gas: Float?
        )