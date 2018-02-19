package com.untref.tesis.server.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.untref.tesis.server.alert.domain.CardinalPoint

class CoordinateDto(

        @get:JsonProperty("d") val degree: Int?,
        @get:JsonProperty("m") val minute: Int?,
        @get:JsonProperty("s") val second: Float?,
        @get:JsonProperty("cp") val cardinalPoint: CardinalPoint?
)