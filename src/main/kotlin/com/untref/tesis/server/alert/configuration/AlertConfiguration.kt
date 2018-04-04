package com.untref.tesis.server.alert.configuration

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.notification.domain.AlertNotificationService
import com.untref.tesis.server.alert.domain.AlertRepository
import com.untref.tesis.server.alert.infrastructure.persistence.file.FileAlertRepository
import com.untref.tesis.server.notification.infrastructure.FirebaseNotificationService
import com.untref.tesis.server.notification.infrastructure.TARGET
import com.untref.tesis.server.utils.Property
import com.untref.tesis.server.utils.PropertyName
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlertConfiguration {

    companion object {
        private const val PATH = "alerts.file.path"
    }

    @Bean
    fun alertRepository(): AlertRepository {
        val path = Property().from(PropertyName.APPLICATION)[PATH] as String
        return FileAlertRepository(path)
    }

    @Bean
    fun alertNotificationService(): AlertNotificationService = FirebaseNotificationService(TARGET)

    @Bean
    fun receiveAlert(alertRepository: AlertRepository, alertNotificationService: AlertNotificationService): ReceiveAlert =
            ReceiveAlert(alertRepository, alertNotificationService)
}