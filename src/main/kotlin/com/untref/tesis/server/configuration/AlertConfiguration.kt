package com.untref.tesis.server.configuration

import com.untref.tesis.server.domain.AlertRepository
import com.untref.tesis.server.infrastructure.persistence.file.FileAlertRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlertConfiguration {

    private val path = "alerts.dat"

    @Bean
    fun alertRepository():AlertRepository = FileAlertRepository(path)
}