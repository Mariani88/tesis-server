package com.untref.tesis.server.unit.notification.infrastructure

import com.untref.tesis.server.notification.infrastructure.FirebaseHeaderFactory
import com.untref.tesis.server.notification.infrastructure.SERVER_KEY
import org.junit.Assert
import org.junit.Test
import org.springframework.http.MediaType

class FirebaseHeaderFactoryTest {

    private val authorization = "Authorization"

    @Test
    fun headerShouldBeCreatedCorrectlyForFirebase() {
        val header = FirebaseHeaderFactory.create()
        Assert.assertEquals(MediaType.APPLICATION_JSON, header.contentType)
        Assert.assertEquals(listOf(SERVER_KEY), header[authorization])
    }
}