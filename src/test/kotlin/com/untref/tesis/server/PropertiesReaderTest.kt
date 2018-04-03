package com.untref.tesis.server

import org.junit.Ignore
import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Properties

class PropertiesReaderTest {

	@Test
	@Ignore
	fun sdksjad() {

		val contextClassLoader = Thread.currentThread().contextClassLoader
		val properties = Properties()
		val resourceStream = contextClassLoader.getResourceAsStream("application.properties")
		properties.load(resourceStream)
	}

}