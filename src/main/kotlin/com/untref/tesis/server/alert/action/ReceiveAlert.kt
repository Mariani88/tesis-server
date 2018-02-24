package com.untref.tesis.server.alert.action

import com.untref.tesis.server.alert.domain.*
import com.untref.tesis.server.notification.domain.AlertNotificationService
import rx.Single
import java.util.*

class ReceiveAlert(private val alertRepository: AlertRepository, private val alertNotificationService: AlertNotificationService) {

    operator fun invoke(receiveAlertActionData: ReceiveAlertActionData) {
        Single.just(alertRepository.lastId())
                .map { buildAlert(it, receiveAlertActionData) }
                .doOnSuccess { alertRepository.store(it) }
                .subscribe({ alertNotificationService.send(it) })
    }

    private fun buildAlert(id: Long, receiveAlertActionData: ReceiveAlertActionData): Alert {
        val latitudeActionData = receiveAlertActionData.coordinates.latitudeActionData
        val longitudeActionData = receiveAlertActionData.coordinates.longitudeActionData
        val latitude = Latitude.build(latitudeActionData.degree, latitudeActionData.minute, latitudeActionData.second, latitudeActionData.cardinalPoint)
        val longitude = Longitude.build(longitudeActionData.degree, longitudeActionData.minute, longitudeActionData.second, longitudeActionData.cardinalPoint)
        val coordinates = Coordinates(latitude, longitude)
        return Alert.build(id, coordinates, receiveAlertActionData.detectionMethods, receiveAlertActionData.temperature,
                receiveAlertActionData.gas, Date())
    }
}