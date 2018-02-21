package com.untref.tesis.server.notification.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.notification.domain.AlertNotificationService
import com.untref.tesis.server.notification.dto.factory.AlertNotificationDtoFactory
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.http.HttpEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

class FirebaseNotificationService(private val target: String) : AlertNotificationService {

    override fun send(alert: Alert) {
        val headers = FirebaseHeaderFactory.create()
        val body = AlertNotificationDtoFactory.create(alert, target)
        val restTemplate = RestTemplate()

        val bodyAsMap = JacksonJsonParser().parseMap(ObjectMapper().writeValueAsString(body))
        val request = HttpEntity<MutableMap<String, Any>>(bodyAsMap, headers)

        //TODO contemplar el caso de 200 pero con falla
        try {
            restTemplate.postForEntity(URL, request, String::class.java)
        } catch (e: HttpClientErrorException) {
            System.out.println("Notification not sent for alert with id: $alert?.id")
        }
    }
}