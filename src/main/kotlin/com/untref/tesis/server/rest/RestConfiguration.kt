package com.untref.tesis.server.rest

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.alert.action.factory.ReceiveAlertActionDataFactory
import com.untref.tesis.server.alert.action.validator.CoordinateValidator
import com.untref.tesis.server.alert.action.validator.LatitudeValidator
import com.untref.tesis.server.alert.action.validator.LongitudeValidator
import com.untref.tesis.server.resource.AlertResource
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class RestConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    fun alertResource(receiveAlert: ReceiveAlert, receiveAlertActionDataFactory: ReceiveAlertActionDataFactory): AlertResource =
            AlertResource(receiveAlert, receiveAlertActionDataFactory)

    @Bean
    fun receiveAlertActionDataFactory(coordinateValidator: CoordinateValidator): ReceiveAlertActionDataFactory
            = ReceiveAlertActionDataFactory(coordinateValidator)

    @Bean
    fun coordinateValidator(latitudeValidator: LatitudeValidator, longitudeValidator: LongitudeValidator): CoordinateValidator =
            CoordinateValidator(latitudeValidator, longitudeValidator)

    @Bean
    fun latitudeValidator(): LatitudeValidator = LatitudeValidator()

    @Bean
    fun longitudeValidator(): LongitudeValidator = LongitudeValidator()
}