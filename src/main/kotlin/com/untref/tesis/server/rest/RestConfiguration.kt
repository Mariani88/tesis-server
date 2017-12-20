package com.untref.tesis.server.rest

import com.untref.tesis.server.resource.AlarmResource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class RestConfiguration {

    @Bean
    fun alarmResource(): AlarmResource = AlarmResource()
}