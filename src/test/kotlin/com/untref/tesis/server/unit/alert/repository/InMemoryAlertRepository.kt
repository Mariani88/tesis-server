package com.untref.tesis.server.unit.alert.repository

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.AlertRepository
import java.util.*

class InMemoryAlertRepository : AlertRepository {

    private val alerts: MutableMap<Long, Alert> = mutableMapOf()

    override fun store(alert: Alert) {
        alerts.put(alert.id, alert)
    }

    override fun find(alertId: Long) = Optional.ofNullable(alerts[alertId])
            .orElseThrow({ RuntimeException("Alert not found") })!!

    override fun lastId(): Long = (alerts.size + 1).toLong()
}