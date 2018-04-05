package com.untref.tesis.server.alert.infrastructure.persistence.file.configuration

import com.untref.tesis.server.alert.domain.AlertRepository
import com.untref.tesis.server.alert.infrastructure.persistence.file.FileAlertRepository
import com.untref.tesis.server.extensions.getValue
import com.untref.tesis.server.properties.Property
import com.untref.tesis.server.properties.PropertyFilePath
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InfrastructureConfiguration {

    companion object {
        private const val PATH = "alerts.file.path"
    }

    @Bean
    fun alertRepository(property: Property): AlertRepository {
        val path = property.from(PropertyFilePath.APPLICATION).getValue<String>(PATH)
        return FileAlertRepository(path)
    }
}