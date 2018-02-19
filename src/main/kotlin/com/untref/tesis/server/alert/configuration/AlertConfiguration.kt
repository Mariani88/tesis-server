package com.untref.tesis.server.alert.configuration

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.notification.domain.AlertNotificationService
import com.untref.tesis.server.alert.domain.AlertRepository
import com.untref.tesis.server.alert.infrastructure.persistence.file.FileAlertRepository
import com.untref.tesis.server.notification.sevice.FirebaseNotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlertConfiguration {

    private val path = "alerts.dat"

    @Bean
    fun alertRepository(): AlertRepository = FileAlertRepository(path)

    @Bean
    fun alertNotificationService(): AlertNotificationService = FirebaseNotificationService()

    @Bean
    fun receiveAlert(alertRepository: AlertRepository, alertNotificationService: AlertNotificationService): ReceiveAlert =
            ReceiveAlert(alertRepository, alertNotificationService)
}