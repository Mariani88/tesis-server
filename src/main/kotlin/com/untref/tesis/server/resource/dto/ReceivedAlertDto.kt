package com.untref.tesis.server.resource.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ReceivedAlertDto (
        @JsonProperty("received")
        val received: Boolean?
        )