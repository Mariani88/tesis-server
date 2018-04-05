package com.untref.tesis.server.notification.infrastructure

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class FirebaseHeaderFactory(private val serverKey: String) {

    fun create(): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set(AUTHORIZATION, serverKey)
        return headers
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}