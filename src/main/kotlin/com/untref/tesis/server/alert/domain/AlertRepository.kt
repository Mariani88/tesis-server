package com.untref.tesis.server.alert.domain

interface AlertRepository {

    fun store(alert: Alert)

    fun find(alertId: Long): Alert

    fun lastId(): Long
}