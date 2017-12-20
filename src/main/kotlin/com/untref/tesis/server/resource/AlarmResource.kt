package com.untref.tesis.server.resource

import com.untref.tesis.server.resource.dto.FireAlertDto
import com.untref.tesis.server.resource.dto.ReceivedAlertDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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

    //TODO testear las validaciones
    /*private fun createReceiveAlertActionData(fireAlertDto: FireAlertDto): ReceiveAlertActionData {
        //val coordinates = validate(fireAlertDto.coordinates)
        //val detectionMethods = validate(fireAlertDto.detectionMethods)
        //val temperature = validate(fireAlertDto.temperature)
        //val gas = validate(fireAlertDto.gas)
        return ReceiveAlertActionData(fireAlertDto.coordinates, detectionMethods, temperature, gas)
    }*/
}