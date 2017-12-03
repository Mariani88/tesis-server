package com.untref.tesis.server.domain

interface AlertNotificationService {
    fun send(alert: Alert)
}