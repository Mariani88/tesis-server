package com.untref.tesis.server.utils

import java.util.*

class Property {

    private val properties: MutableMap<PropertyName, Properties> = mutableMapOf()

    init {
        PropertyName.values().forEach { properties[it] = loadPropertyFile(it.name.toLowerCase()) }
    }

    fun from(propertyName: PropertyName) = properties[propertyName]!!

    private fun loadPropertyFile(propertyName: String): Properties {
        val properties = Properties()
        val contextClassLoader = Thread.currentThread().contextClassLoader
        val resourceStream = contextClassLoader.getResourceAsStream("$propertyName.properties")
        properties.load(resourceStream)
        return properties
    }
}