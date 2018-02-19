package com.untref.tesis.server.resource

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.alert.action.ReceiveAlertActionData
import com.untref.tesis.server.alert.action.factory.ReceiveAlertActionDataFactory
import com.untref.tesis.server.alert.domain.*
import com.untref.tesis.server.notification.infrastructure.FirebaseNotificationService
import com.untref.tesis.server.resource.dto.FireAlertDto
import com.untref.tesis.server.resource.dto.ReceivedAlertDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AlarmResource(private val receiveAlert: ReceiveAlert, private val receiveAlertActionDataFactory: ReceiveAlertActionDataFactory) {

    @PostMapping("/alert")
    fun receiveAlert(@RequestBody fireAlertDto: FireAlertDto): ReceivedAlertDto {
        System.out.println("pass")
        receiveAlert(createReceiveAlertActionData(fireAlertDto))
        return ReceivedAlertDto(true)
    }

    @GetMapping("/alert")
    fun test(): ReceivedAlertDto {
        System.out.println("pass")

        val alert = Alert(1L, Coordinates(Coordinate(1,1,1f, CardinalPoint.SOUTH),
                Coordinate(1,1,1f, CardinalPoint.SOUTH)), listOf(), 34f, 34f)

        FirebaseNotificationService().send(alert)

        return ReceivedAlertDto(true)
    }

    private fun createReceiveAlertActionData(fireAlertDto: FireAlertDto): ReceiveAlertActionData {
        return receiveAlertActionDataFactory.create(fireAlertDto)
    }
}