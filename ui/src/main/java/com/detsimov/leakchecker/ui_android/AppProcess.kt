package com.detsimov.leakchecker.ui_android

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context

object AppProcess {
    private lateinit var applicationContext: Context

    lateinit var value: String
        private set

    fun init(application: Application) {
        applicationContext = application.applicationContext
        value = application.processName ?: ""
    }

    fun isEqual(process: Process): Boolean = when (process) {
        Process.REMOTE -> resolveWorkManagerProcess(process) == value
        Process.MAIN -> applicationContext.packageName == value
    }


    @SuppressLint("DefaultLocale")
    fun resolveWorkManagerProcess(process: Process) =
        applicationContext.packageName.plus(":${process.name.toLowerCase()}")

}

val Application.processName: String?
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