package com.untref.tesis.server

import java.util.Properties

class Properties {

	companion object {

		fun get(){
			val contextClassLoader = Thread.currentThread().contextClassLoader
			val properties = Properties()
			val resourceStream = contextClassLoader.getResourceAsStream("application.properties")
			properties.load(resourceStream)
		}
	}

}