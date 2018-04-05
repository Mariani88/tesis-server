package com.untref.tesis.server.notification.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.notification.domain.AlertNotificationService
import com.untref.tesis.server.notification.dto.factory.AlertNotificationDtoFactory
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.http.HttpEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

class FirebaseNotificationService(private val target: String, private val url: String, private val firebaseHeaderFactory: FirebaseHeaderFactory) : AlertNotificationService {

    override fun send(alert: Alert) {
        val headers = firebaseHeaderFactory.create()
        val body = AlertNotificationDtoFactory.create(alert, target)
        val restTemplate = RestTemplate()

        val bodyAsMap = JacksonJsonParser().parseMap(ObjectMapper().writeValueAsString(body))
        val request = HttpEntity<MutableMap<String, Any>>(bodyAsMap, headers)

        try {
            restTemplate.postForEntity(url, request, String::class.java)
        } catch (e: Exception) {
            System.out.println("Notification not sent for alert with id: $alert?.id")
            System.out.println(e)
            System.out.println(e.stackTrace)
        }
    }
}