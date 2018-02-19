package com.untref.tesis.server.notification.dto

import com.untref.tesis.server.alert.domain.Alert

class AlertNotificationDtoFactory {

    companion object {

        fun create(alert: Alert, target: String): AlertNotificationDto {
            val notificationBodyDto = NotificationBodyDto("ingresa para ver la localizacion", "ALERTA DE INCENDIO", "myicon")

            val alertLatitude = alert.coordinates.latitude
            val alertLongitude = alert.coordinates.longitude

            val latitude = CoordinateDto(alertLatitude.degree, alertLatitude.minute, alertLatitude.second, alertLatitude.cardinalPoint)
            val longitude = CoordinateDto(alertLongitude.degree, alertLongitude.minute, alertLongitude.second, alertLongitude.cardinalPoint)

            val notificationDataDto = NotificationDataDto(latitude, longitude)

            return AlertNotificationDto(target, notificationBodyDto, notificationDataDto)
        }
    }
}