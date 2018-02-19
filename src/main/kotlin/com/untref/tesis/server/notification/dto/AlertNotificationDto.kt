package com.untref.tesis.server.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AlertNotificationDto(
        @get:JsonProperty("to") val target: String?,
        @get:JsonProperty("notification") val notificationBodyDto: NotificationBodyDto?,
        @get:JsonProperty("data") val notificationDataDto: NotificationDataDto?
)