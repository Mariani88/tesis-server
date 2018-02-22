package com.untref.tesis.server.unit.notification.factory

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.Latitude
import com.untref.tesis.server.alert.domain.Longitude
import com.untref.tesis.server.builders.AlertBuilder.Companion.createAlert
import com.untref.tesis.server.notification.dto.AlertNotificationDto
import com.untref.tesis.server.notification.dto.CoordinateDto
import com.untref.tesis.server.notification.dto.NotificationDataDto
import com.untref.tesis.server.notification.dto.factory.AlertNotificationDtoFactory
import org.junit.Assert
import org.junit.Test
import java.util.*

class AlertNotificationDtoFactoryTest {

    private lateinit var alert: Alert
    private lateinit var target: String
    private lateinit var alertNotificationDto: AlertNotificationDto

    @Test
    fun createNotificationDtoShouldCreateCorrectly() {
        givenATarget()
        givenAnAlert()

        whenCreateAlertNotificationDto()

        thenCreateWithValidValues()
    }

    private fun thenCreateWithValidValues() {
        Assert.assertNotNull(alertNotificationDto.target)
        Assert.assertEquals(target, alertNotificationDto.target!!)
        Assert.assertNotNull(alertNotificationDto.notificationDataDto)
        assertContentData(alertNotificationDto.notificationDataDto!!)
    }

    private fun whenCreateAlertNotificationDto() {
        alertNotificationDto = AlertNotificationDtoFactory.create(alert, target)
    }

    private fun givenAnAlert() {
        alert = createAlert()
    }

    private fun givenATarget() {
        target = "topic"
    }

    private fun assertContentData(notificationDataDto: NotificationDataDto) {
        Assert.assertEquals(alert.date, Date(notificationDataDto.date))
        Assert.assertEquals(alert.id, notificationDataDto.id)
        Assert.assertNotNull(notificationDataDto.latitude)
        Assert.assertNotNull(notificationDataDto.longitude)
        assertLatitude(notificationDataDto.latitude!!, alert.coordinates.latitude)
        assertLongitude(notificationDataDto.longitude!!, alert.coordinates.longitude)
    }

    private fun assertLatitude(coordinateDto: CoordinateDto, latitude: Latitude) {
        Assert.assertEquals(latitude.degree, coordinateDto.degree)
        Assert.assertEquals(latitude.minute, coordinateDto.minute)
        Assert.assertEquals(latitude.second, coordinateDto.second)
        Assert.assertEquals(latitude.cardinalPoint, coordinateDto.cardinalPoint)
    }

    private fun assertLongitude(coordinateDto: CoordinateDto, longitude: Longitude) {
        Assert.assertEquals(longitude.degree, coordinateDto.degree)
        Assert.assertEquals(longitude.minute, coordinateDto.minute)
        Assert.assertEquals(longitude.second, coordinateDto.second)
        Assert.assertEquals(longitude.cardinalPoint, coordinateDto.cardinalPoint)
    }
}