package com.untref.tesis.server.properties.configuration

import com.untref.tesis.server.properties.Property
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PropertyConfiguration {

    @Bean
    fun property() = Property()
}