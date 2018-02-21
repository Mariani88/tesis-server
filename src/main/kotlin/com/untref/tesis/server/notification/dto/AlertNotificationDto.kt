package com.untref.tesis.server.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty

//@get is necessary for serialize. if @get is removed, this not work
//see https://github.com/FasterXML/jackson-module-kotlin/issues/36

data class AlertNotificationDto(
        @get:JsonProperty("to") val target: String?,
        @get:JsonProperty("data") val notificationDataDto: NotificationDataDto?
)