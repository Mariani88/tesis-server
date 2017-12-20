package com.untref.tesis.server.infrastructure.persistence.file

private val canNotStore = "can not store alert"
private val canNotAccess = "can not access to alert file"

fun canNotStore() = canNotStore

fun canNotAccess() = canNotAccess

fun alertNotExist(alertId: Long) = "alert with id $alertId not exist"