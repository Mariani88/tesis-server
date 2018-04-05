package com.untref.tesis.server.notification.configuration

import com.untref.tesis.server.extensions.getValue
import com.untref.tesis.server.notification.domain.AlertNotificationService
import com.untref.tesis.server.notification.infrastructure.FirebaseHeaderFactory
import com.untref.tesis.server.notification.infrastructure.FirebaseNotificationService
import com.untref.tesis.server.properties.Property
import com.untref.tesis.server.properties.PropertyFilePath
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotificationConfiguration {

    companion object {
        private const val TARGET = "target"
        private const val URL = "url"
        private const val SERVER_KEY = "server.key"
    }

    @Bean
    fun alertNotificationService(property: Property, firebaseHeaderFactory: FirebaseHeaderFactory): AlertNotificationService {
        val target = property.from(PropertyFilePath.FIREBASE).getValue<String>(TARGET)
        val url = property.from(PropertyFilePath.FIREBASE).getValue<String>(URL)
        return FirebaseNotificationService(target, url, firebaseHeaderFactory)
    }

    @Bean
    fun firebaseHeaderFactory(property: Property): FirebaseHeaderFactory {
        val serverKey = property.from(PropertyFilePath.FIREBASE).getValue<String>(SERVER_KEY)
        return FirebaseHeaderFactory(serverKey)
    }
}