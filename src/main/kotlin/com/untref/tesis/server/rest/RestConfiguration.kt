package com.untref.tesis.server.rest

import com.untref.tesis.server.action.ReceiveAlert
import com.untref.tesis.server.action.factory.ReceiveAlertActionDataFactory
import com.untref.tesis.server.action.validator.CoordinateValidator
import com.untref.tesis.server.action.validator.LatitudeValidator
import com.untref.tesis.server.action.validator.LongitudeValidator
import com.untref.tesis.server.resource.AlarmResource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestConfiguration {

    @Bean
    fun alarmResource(receiveAlert: ReceiveAlert, receiveAlertActionDataFactory: ReceiveAlertActionDataFactory): AlarmResource =
            AlarmResource(receiveAlert, receiveAlertActionDataFactory)

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