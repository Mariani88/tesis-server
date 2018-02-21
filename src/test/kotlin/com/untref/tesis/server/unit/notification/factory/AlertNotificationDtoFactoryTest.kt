package com.untref.tesis.server.unit.notification.factory

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.Coordinate
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
        assertCoordinate(notificationDataDto.latitude!!, alert.coordinates.latitude)
        assertCoordinate(notificationDataDto.longitude!!, alert.coordinates.longitude)
    }

    private fun assertCoordinate(coordinateDto: CoordinateDto, coordinate: Coordinate) {
        Assert.assertEquals(coordinate.degree, coordinateDto.degree)
        Assert.assertEquals(coordinate.minute, coordinateDto.minute)
        Assert.assertEquals(coordinate.second, coordinateDto.second)
        Assert.assertEquals(coordinate.cardinalPoint, coordinateDto.cardinalPoint)
    }
}