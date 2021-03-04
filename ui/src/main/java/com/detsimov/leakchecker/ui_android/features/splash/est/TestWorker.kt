package com.detsimov.leakchecker.ui_android.features.splash.est

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.edit
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.detsimov.leakchecker.ui_android.R
import com.kirich1409.androidnotificationdsl.channels.createNotificationChannels
import com.kirich1409.androidnotificationdsl.notify
import kotlin.random.Random

class TestWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    companion object {
        const val TAG = "TestWorker"
        const val KEY_COUNT = "KEY_COUNT"
    }
    private val sharedPreferences = appContext.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    override fun doWork(): Result {
        sharedPreferences.edit {
            putInt(KEY_COUNT, sharedPreferences.getInt(KEY_COUNT, 0) + 1)
        }
        createNotificationChannels(applicationContext) {
            channel("TEST", "TEST CHANNEL")
        }
        notify(applicationContext, Random.nextInt(10000), "TEST", R.drawable.ic_launcher_foreground) {
            contentTitle("Hello world!")
            contentText("How are you?")
            priority(NotificationCompat.PRIORITY_HIGH)
        }
        return Result.success()
    }
}