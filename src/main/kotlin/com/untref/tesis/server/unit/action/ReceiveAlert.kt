package com.untref.tesis.server.unit.action

import com.untref.tesis.server.domain.Alert
import com.untref.tesis.server.domain.AlertNotificationService
import com.untref.tesis.server.domain.AlertRepository
import rx.Single

class ReceiveAlert(private val alertRepository: AlertRepository, private val alertNotificationService: AlertNotificationService) {

    operator fun invoke(receiveAlertActionData: ReceiveAlertActionData) {
        Single.just(alertRepository.lastId())
                .map { buildAlert(it, receiveAlertActionData) }
                .doOnSuccess { alertRepository.storeAlert(it) }
                .subscribe({ alertNotificationService.send(it) })
    }

    private fun buildAlert(id: Long, receiveAlertActionData: ReceiveAlertActionData): Alert {
        return Alert(id, receiveAlertActionData.coordinates,
                receiveAlertActionData.detectionMethods,
                receiveAlertActionData.temperature,
                receiveAlertActionData.gas)
    }
}