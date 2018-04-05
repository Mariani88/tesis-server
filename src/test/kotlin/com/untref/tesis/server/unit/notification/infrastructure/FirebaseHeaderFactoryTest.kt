package com.untref.tesis.server.unit.notification.infrastructure

import com.untref.tesis.server.notification.infrastructure.FirebaseHeaderFactory
import org.junit.Assert
import org.junit.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class FirebaseHeaderFactoryTest {

    private val authorization = "Authorization"
    private lateinit var header: HttpHeaders
    private lateinit var serverKey: String

    @Test
    fun headerShouldBeCreatedCorrectlyForFirebase() {
        givenAServerKey()

        whenCreateHeader()

        thenHeaderIsCreatedCorrectly()
    }

    private fun thenHeaderIsCreatedCorrectly() {
        Assert.assertEquals(MediaType.APPLICATION_JSON, header.contentType)
        Assert.assertEquals(listOf(serverKey), header[authorization])
    }

    private fun whenCreateHeader() {
        header = FirebaseHeaderFactory(serverKey).create()
    }

    private fun givenAServerKey() {
        serverKey = "server-key"
    }
}