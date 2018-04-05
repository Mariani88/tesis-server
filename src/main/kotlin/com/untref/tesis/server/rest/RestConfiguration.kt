package com.untref.tesis.server.rest

import com.untref.tesis.server.alert.action.ReceiveAlert
import com.untref.tesis.server.alert.action.factory.ReceiveAlertActionDataFactory
import com.untref.tesis.server.alert.action.validator.CoordinateDtoPropertyValidator
import com.untref.tesis.server.alert.action.validator.CoordinateValidator
import com.untref.tesis.server.notification.infrastructure.FirebaseNotificationService
import com.untref.tesis.server.resource.AlertResource
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class RestConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    fun alertResource(receiveAlert: ReceiveAlert, receiveAlertActionDataFactory: ReceiveAlertActionDataFactory, firebaseNotificationService: FirebaseNotificationService): AlertResource =
            AlertResource(receiveAlert, receiveAlertActionDataFactory, firebaseNotificationService)

    @Bean
    fun receiveAlertActionDataFactory(coordinateValidator: CoordinateValidator): ReceiveAlertActionDataFactory = ReceiveAlertActionDataFactory(coordinateValidator)

    @Bean
    fun coordinateValidator(coordinateDtoPropertyValidator: CoordinateDtoPropertyValidator): CoordinateValidator =
            CoordinateValidator(coordinateDtoPropertyValidator)

    @Bean
    fun coordinateDtoPropertyValidator(): CoordinateDtoPropertyValidator = CoordinateDtoPropertyValidator()
}