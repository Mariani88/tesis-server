package com.untref.tesis.server.notification.infrastructure

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class FirebaseHeaderFactory {

    companion object {
        private const val AUTHORIZATION = "Authorization"

        fun create(): HttpHeaders {
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON
            headers.set(AUTHORIZATION, SERVER_KEY)
            return headers
        }
    }
}