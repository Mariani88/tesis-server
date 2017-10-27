package server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AlertDto(
        @JsonProperty("alarm")
        val alarm: String?

        )