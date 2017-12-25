package com.untref.tesis.server.domain

interface AlertRepository {

    fun store(alert: Alert)

    fun find(alertId: Long): Alert

    fun lastId(): Long
}