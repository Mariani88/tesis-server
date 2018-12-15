package com.untref.tesis.server.alert.infrastructure.persistence.file

import com.untref.tesis.server.alert.domain.Alert
import com.untref.tesis.server.alert.domain.AlertRepository
import com.untref.tesis.server.alert.infrastructure.persistence.file.entities.AlertEntity
import java.io.*
import java.util.*

class FileAlertRepository (private val path: String): AlertRepository {

    private var lastId = getAll().size.toLong() + 1

    override fun store(alert: Alert) {
        val alerts = getAll()
        val oos = ObjectOutputStream(FileOutputStream(path))
        alerts[alert.id] = AlertEntity.from(alert)
        tryExecute({ oos.writeObject(alerts) }, { oos.close() }, canNotStore())
        lastId += 1
    }

    override fun find(alertId: Long): Alert =
            Optional.ofNullable(getAll()[alertId]).map { it.toAlert() }.orElseThrow({ RuntimeException(alertNotExist(alertId)) })

    override fun lastId(): Long = lastId

    private fun getAll(): MutableMap<Long, AlertEntity> {
        if (!File(path).exists()) return mutableMapOf()
        val ois = ObjectInputStream(FileInputStream(path))
        return tryExecute({ ois.readObject() as MutableMap<Long, AlertEntity> }, { ois.close() }, canNotAccess())
    }

    private fun <T> tryExecute(tryExecute: () -> T, finallyExecute: () -> Unit, message: String): T {
        try {
            return tryExecute()
        } catch (e: Exception) {
            throw RuntimeException(message)
        } finally {
            finallyExecute()
        }
    }
}