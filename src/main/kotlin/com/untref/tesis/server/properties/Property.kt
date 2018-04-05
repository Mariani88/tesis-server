package com.untref.tesis.server.properties

import java.util.*

class Property {

    private val properties: MutableMap<PropertyFilePath, Properties> = mutableMapOf()

    init {
        PropertyFilePath.values().forEach { properties[it] = loadPropertyFile(it.name.toLowerCase()) }
    }

    fun from(propertyFilePath: PropertyFilePath):Properties = properties[propertyFilePath]!!

    private fun loadPropertyFile(propertyName: String): Properties {
        val properties = Properties()
        val contextClassLoader = Thread.currentThread().contextClassLoader
        val resourceStream = contextClassLoader.getResourceAsStream("$propertyName.properties")
        properties.load(resourceStream)
        return properties
    }
}