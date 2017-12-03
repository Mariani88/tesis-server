package com.untref.tesis.server.domain

interface AlertRepository {

    fun storeAlert(alert: Alert)

    fun findById(alertId: Long): Alert
    fun lastId(): Long
}