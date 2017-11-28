package server.resource

import org.springframework.web.bind.annotation.GetMapping
import server.resource.dto.FireAlertDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import server.action.ReceiveAlert
import server.resource.dto.ReceivedAlertDto


@RestController
class AlarmResource {

    @PostMapping("/alert")
    fun receiveAlert(@RequestBody fireAlertDto: FireAlertDto): ReceivedAlertDto {
        System.out.println("pass")
        ReceiveAlert()(fireAlertDto)
        return ReceivedAlertDto(true)
    }

    @GetMapping("/alert")
    fun test(): ReceivedAlertDto {
        System.out.println("pass")
        return ReceivedAlertDto(true)
    }
}