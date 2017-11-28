package server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CoordinatesDto(
        @JsonProperty("lat")
        val latitude: String?,

        @JsonProperty("long")
        val longitude: String?
)