package com.untref.tesis.server.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.untref.tesis.server.alert.domain.CardinalPoint

class CoordinateDto(
        @get:JsonProperty("degree") val degree: Int?,
        @get:JsonProperty("minute") val minute: Int?,
        @get:JsonProperty("second") val second: Double?,
        @get:JsonProperty("cardinal_point") val cardinalPoint: CardinalPoint?
)