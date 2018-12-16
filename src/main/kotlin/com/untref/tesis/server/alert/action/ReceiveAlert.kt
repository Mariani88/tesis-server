package com.untref.tesis.server.alert.action

import com.untref.tesis.server.alert.domain.*
import com.untref.tesis.server.notification.domain.AlertNotificationService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.exceptions.Exceptions
import io.reactivex.schedulers.Schedulers
import java.util.*

class ReceiveAlert(private val alertRepository: AlertRepository, private val alertNotificationService: AlertNotificationService) {

    operator fun invoke(receiveAlertActionData: ReceiveAlertActionData) {
        val lastId = alertRepository.lastId()
        val alert = buildAlert(lastId, receiveAlertActionData)
        alertRepository.store(alert)
        sendAlert(alert)
    }

    private fun sendAlert(it: Alert) {
        Completable.fromAction { alertNotificationService.send(it) }
                .subscribeOn(Schedulers.newThread())
                .subscribe()
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