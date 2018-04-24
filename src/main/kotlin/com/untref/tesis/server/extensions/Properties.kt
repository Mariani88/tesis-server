package com.untref.tesis.server.extensions

import java.util.*

fun Properties.getString(propertyName: String): String = this.getProperty(propertyName)