package com.untref.tesis.server.alert.action

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.notification.domain.AlertNotificationService
import com.untref.tesis.server.alert.domain.AlertRepository
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
        return Alert(id, receiveAlertActionData.coordinates,
                receiveAlertActionData.detectionMethods,
                receiveAlertActionData.temperature,
                receiveAlertActionData.gas,
                Date()
        )
    }
}