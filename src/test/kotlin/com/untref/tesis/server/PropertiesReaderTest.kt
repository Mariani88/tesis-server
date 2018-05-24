package com.untref.tesis.server

import com.untref.tesis.server.properties.Property
import com.untref.tesis.server.properties.PropertyFilePath
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test

class PropertiesReaderTest {

    private lateinit var property: Property
    private lateinit var propertyFilePath: PropertyFilePath
    private lateinit var propertyName: String
    private var exception: Throwable? = null

    @Test
    fun loadApplicationPropertiesSuccessfully() {
        givenAProperty()
        givenAnApplicationProperty()

        whenLoadProperty()

        thenLoadSuccessfully()
    }

    @Test
    fun loadFirebasePropertiesSuccessfully() {
        givenAProperty()
        givenAFirebaseProperty()

        whenLoadProperty()

        thenLoadSuccessfully()
    }

    private fun givenAFirebaseProperty() {
        propertyFilePath = PropertyFilePath.FIREBASE
        propertyName = "url"
    }

    private fun givenAnApplicationProperty() {
        propertyFilePath = PropertyFilePath.APPLICATION
        propertyName = "alerts.file.path"
    }

    private fun givenAProperty() {
        property = Property()
    }

    private fun whenLoadProperty() {
        catchThrowable { property.from(propertyFilePath).getProperty(propertyName) }
    }

    private fun thenLoadSuccessfully() {
        assertThat(exception).isNull()
    }
}
