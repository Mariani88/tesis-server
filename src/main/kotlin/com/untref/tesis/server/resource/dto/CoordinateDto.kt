package com.untref.tesis.server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.untref.tesis.server.alert.domain.CardinalPoint

class CoordinateDto(
        @JsonProperty("d") val degree: Int?,
        @JsonProperty("m") val minute: Int?,
        @JsonProperty("s") val second: Double?,
        @JsonProperty("cp") val cardinalPoint: CardinalPoint?
)