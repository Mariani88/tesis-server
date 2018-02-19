package com.untref.tesis.server.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty

class NotificationDataDto (

        @get:JsonProperty("latitude")
        val latitude: CoordinateDto?,

        @get:JsonProperty("longitude")
        val longitude: CoordinateDto?
)