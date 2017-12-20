package com.untref.tesis.server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CoordinatesDto(
        @JsonProperty("lat") val latitude: CoordinateDto?,
        @JsonProperty("long") val longitude: CoordinateDto?
)