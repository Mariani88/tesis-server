package com.untref.tesis.server.infrastructure.persistence

import com.untref.tesis.server.domain.Alert
import com.untref.tesis.server.domain.AlertRepository
import java.io.*
import java.util.*

class FileAlertRepository : AlertRepository {

    private var lastId = 1L
    private val path = "alerts.dat"



    override fun storeAlert(alert: Alert) {
        val alerts = getAll()
        val oos = ObjectOutputStream(FileOutputStream(path))
        alerts.put(alert.id, alert)

        try {
            oos.writeObject(alerts)
        } catch (e: Exception) {
            throw RuntimeException("can not store alert")
        } finally {
            oos.close()
        }

        lastId += 1
    }

    override fun findById(alertId: Long): Alert =
            Optional.ofNullable(getAll()[alertId]).orElseThrow({ RuntimeException("alert with id $alertId not exist") })

    override fun lastId(): Long = lastId

    private fun getAll(): MutableMap<Long, Alert> {
        if(!File(path).exists()) return mutableMapOf()

        val ois = ObjectInputStream(FileInputStream(path))

        try {
            return ois.readObject() as MutableMap<Long, Alert>
        } catch (e: Exception) {
            throw RuntimeException("can not access to alert file")
        } finally {
            ois.close()
        }
    }

    private fun checkFileExist() {
        if (!File(path).exists()) createEmptyFile()
    }

    private fun createEmptyFile() {
        val oos = ObjectOutputStream(FileOutputStream(path))
        try {
            oos.writeObject(AlertsEntity(mutableMapOf<Long, Alert>()))
        } catch (e: Exception) {
            throw RuntimeException("can not create alert file")
        } finally {
            oos.close()
        }
    }
}

class AlertsEntity(val mutableMapOf: MutableMap<Long, Alert>)