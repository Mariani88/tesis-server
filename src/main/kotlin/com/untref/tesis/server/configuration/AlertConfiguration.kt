package com.untref.tesis.server.configuration

import com.untref.tesis.server.action.ReceiveAlert
import com.untref.tesis.server.domain.AlertNotificationService
import com.untref.tesis.server.domain.AlertRepository
import com.untref.tesis.server.infrastructure.persistence.file.FileAlertRepository
import com.untref.tesis.server.infrastructure.sevice.AndroidAlertNotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlertConfiguration {

    private val path = "alerts.dat"

    @Bean
    fun alertRepository(): AlertRepository = FileAlertRepository(path)

    @Bean
    fun alertNotificationService(): AlertNotificationService = AndroidAlertNotificationService()

    @Bean
    fun receiveAlert(alertRepository: AlertRepository, alertNotificationService: AlertNotificationService): ReceiveAlert =
            ReceiveAlert(alertRepository, alertNotificationService)
}