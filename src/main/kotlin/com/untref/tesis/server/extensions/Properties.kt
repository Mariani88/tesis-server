package com.untref.tesis.server.extensions

import java.util.*

fun <T> Properties.getValue(propertyName: String):T = this[propertyName] as T