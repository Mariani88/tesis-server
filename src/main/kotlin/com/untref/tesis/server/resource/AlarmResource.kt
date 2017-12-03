package com.untref.tesis.server.resource

import org.springframework.web.bind.annotation.GetMapping
import com.untref.tesis.server.resource.dto.FireAlertDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.untref.tesis.server.action.ReceiveAlert
import com.untref.tesis.server.action.ReceiveAlertActionData
import com.untref.tesis.server.resource.dto.ReceivedAlertDto


@RestController
class AlarmResource {

    @PostMapping("/alert")
    fun receiveAlert(@RequestBody fireAlertDto: FireAlertDto): ReceivedAlertDto {
        System.out.println("pass")
        //ReceiveAlert()(createReceiveAlertActionData(fireAlertDto))
        return ReceivedAlertDto(true)
    }

    @GetMapping("/alert")
    fun test(): ReceivedAlertDto {
        System.out.println("pass")
        return ReceivedAlertDto(true)
    }

    /*//TODO testear las validaciones
    private fun createReceiveAlertActionData(fireAlertDto: FireAlertDto): ReceiveAlertActionData {
        val coordinates = validate(fireAlertDto.coordinates)
        val detectionMethods = validate(fireAlertDto.detectionMethods)
        val temperature = validate(fireAlertDto.temperature)
        val gas = validate(fireAlertDto.gas)
        return ReceiveAlertActionData(coordinates, detectionMethods, temperature, gas)
    }*/
}