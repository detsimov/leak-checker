package com.detsimov.leakchecker.ui_android

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context

object AppProcess {

    private lateinit var applicationContext: Context

    lateinit var value: String
        private set

    fun init(applicationContext: Context) {
        this.applicationContext = applicationContext
        value = applicationContext.processName ?: ""
    }

    fun isEqual(process: Process): Boolean = when (process) {
        Process.REMOTE -> resolveWorkManagerProcess(process) == value
        Process.MAIN -> applicationContext.packageName == value
    }

    fun isUndefined(): Boolean = Process.values().none { isEqual(it) }

    @SuppressLint("DefaultLocale")
    fun resolveWorkManagerProcess(process: Process): String {
        return applicationContext.packageName.plus(":${process.name.toLowerCase()}")
    }
}

val Context.processName: String?
    get() {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName
            }
        }
        return null
    }

enum class Process {
    REMOTE,
    MAIN,
}