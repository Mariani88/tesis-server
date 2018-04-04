package com.untref.tesis.server

import java.util.Properties

class Property {

	private val properties: MutableMap<PropertyName, Properties> = mutableMapOf()

	init {
		PropertyName.values().forEach { properties[it] = loadPropertyFile(it.name) }
	}

	private fun loadPropertyFile(propertyName: String): Properties {
		val properties = Properties()
		val contextClassLoader = Thread.currentThread().contextClassLoader
		val resourceStream = contextClassLoader.getResourceAsStream("$propertyName.properties")
		properties.load(resourceStream)
		return properties
	}
}

enum class PropertyName {
	APPLICATION, FIREBASE
}