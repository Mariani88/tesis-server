package com.untref.tesis.server.notification.dto.factory

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.notification.dto.AlertNotificationDto
import com.untref.tesis.server.notification.dto.CoordinateDto
import com.untref.tesis.server.notification.dto.NotificationDataDto

class AlertNotificationDtoFactory {

    companion object {

        fun create(alert: Alert, target: String): AlertNotificationDto {
            val alertLatitude = alert.coordinates.latitude
            val alertLongitude = alert.coordinates.longitude
            val latitude = CoordinateDto(alertLatitude.degree, alertLatitude.minute, alertLatitude.second, alertLatitude.cardinalPoint)
            val longitude = CoordinateDto(alertLongitude.degree, alertLongitude.minute, alertLongitude.second, alertLongitude.cardinalPoint)
            val notificationDataDto = NotificationDataDto( alert.id,latitude, longitude, alert.date.time)

            return AlertNotificationDto(target, notificationDataDto)
        }
    }
}