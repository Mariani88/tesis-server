package com.untref.tesis.server.action

import com.untref.tesis.server.domain.Alert
import com.untref.tesis.server.domain.AlertRepository
import rx.Single


class ReceiveAlert(private val alertRepository: AlertRepository) {

    operator fun invoke(receiveAlertActionData: ReceiveAlertActionData) {
        Single.just(alertRepository.lastId())
                .map { buildAlert(it, receiveAlertActionData) }
                .doOnSuccess { alertRepository.storeAlert(it) }
                .subscribe({sendAlert()})
    }

    private fun sendAlert() {

    }

    private fun buildAlert(id: Long, receiveAlertActionData: ReceiveAlertActionData): Alert {
        return Alert(id, receiveAlertActionData.coordinates,
                receiveAlertActionData.detectionMethods,
                receiveAlertActionData.temperature,
                receiveAlertActionData.gas)
    }
}