package com.untref.tesis.server.alert.configuration

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.alert.domain.AlertRepository
import com.untref.tesis.server.notification.domain.AlertNotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlertConfiguration {

    @Bean
    fun receiveAlert(alertRepository: AlertRepository, alertNotificationService: AlertNotificationService): ReceiveAlert =
            ReceiveAlert(alertRepository, alertNotificationService)
}