package com.untref.tesis.server.unit.repository

import com.untref.tesis.server.domain.Alert
import com.untref.tesis.server.domain.AlertRepository
import java.util.*

class InMemoryAlertRepository : AlertRepository {

    private val alerts: MutableMap<Long, Alert> = mutableMapOf()

    override fun storeAlert(alert: Alert) {
        alerts.put(alert.id, alert)
    }

    override fun findById(alertId: Long) = Optional.ofNullable(alerts[alertId])
            .orElseThrow({ RuntimeException("Alert not found") })!!

    override fun lastId(): Long = (alerts.size + 1).toLong()
}