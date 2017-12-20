package com.untref.tesis.server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.untref.tesis.server.domain.CardinalPoint

class CoordinateDto(
        @JsonProperty("d") private val degree: Int?,
        @JsonProperty("m") private val minute: Int?,
        @JsonProperty("s") private val second: Float?,
        @JsonProperty("cp") private val cardinalPoint: CardinalPoint?
)