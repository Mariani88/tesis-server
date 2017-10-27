package server.resource

import org.springframework.web.bind.annotation.GetMapping
import server.resource.dto.AlertDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import server.action.ReceiveAlert
import server.resource.dto.ReceivedAlertDto
import org.apache.catalina.filters.RequestDumperFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean


@RestController
class AlarmResource {

    @PostMapping("/alert")
    fun receiveAlert(@RequestBody alertDto: AlertDto): ReceivedAlertDto {
        System.out.println("pass")
        ReceiveAlert()(alertDto)
        return ReceivedAlertDto(true)
    }

    @GetMapping("/alert")
    fun test(): ReceivedAlertDto {
        System.out.println("pass")
        return ReceivedAlertDto(true)
    }
}