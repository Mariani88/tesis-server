package com.untref.tesis.server.notification.domain

import com.untref.tesis.server.alert.domain.Alert

interface AlertNotificationService {
    fun send(alert: Alert?)
}