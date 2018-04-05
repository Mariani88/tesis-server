package com.untref.tesis.server.resource

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.alert.action.ReceiveAlertActionData
import com.untref.tesis.server.alert.action.factory.ReceiveAlertActionDataFactory
import com.untref.tesis.server.alert.domain.*
import com.untref.tesis.server.extensions.getValue
import com.untref.tesis.server.notification.infrastructure.FirebaseNotificationService
import com.untref.tesis.server.properties.Property
import com.untref.tesis.server.properties.PropertyFilePath
import com.untref.tesis.server.resource.dto.FireAlertDto
import com.untref.tesis.server.resource.dto.ReceivedAlertDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class AlertResource(private val receiveAlert: ReceiveAlert,
                    private val receiveAlertActionDataFactory: ReceiveAlertActionDataFactory,
                    private val firebaseNotificationService: FirebaseNotificationService) {

    @PostMapping("/alert")
    fun receiveAlert(@RequestBody fireAlertDto: FireAlertDto): ReceivedAlertDto {
        System.out.println("pass")
        receiveAlert(createReceiveAlertActionData(fireAlertDto))
        return ReceivedAlertDto(true)
    }

    @GetMapping("/alert")
    fun test(): ReceivedAlertDto {
        System.out.println("pass")

        val alert = Alert.build((Random().nextDouble() * 1000).toLong(), Coordinates(Latitude.build(34,33,15.8, CardinalPoint.SOUTH),
                Longitude.build(58,36,32.61, CardinalPoint.WEST)), listOf(DetectionMethod.FIRE), 34f, 34f, Date())

        firebaseNotificationService.send(alert)

        return ReceivedAlertDto(true)
    }

    private fun createReceiveAlertActionData(fireAlertDto: FireAlertDto): ReceiveAlertActionData {
        return receiveAlertActionDataFactory.create(fireAlertDto)
    }
}