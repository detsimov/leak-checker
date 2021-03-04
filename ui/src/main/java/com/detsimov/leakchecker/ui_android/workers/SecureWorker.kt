package com.detsimov.leakchecker.ui_android.workers

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.detsimov.leakchecker.domain.interactors.i.ISecureInteractor
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.common.inject
import com.kirich1409.androidnotificationdsl.channels.createNotificationChannels
import com.kirich1409.androidnotificationdsl.notify
import java.net.UnknownHostException
import kotlin.random.Random

class SecureWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        const val TAG = "SecureWorker"
    }

    private val secureInteractor by inject<ISecureInteractor>()

    override suspend fun doWork(): Result {
        val result = try {
            secureInteractor.fullScan()
        } catch (ignore: UnknownHostException) {
            return Result.retry()
        } catch (any: Throwable) {
            return Result.failure()
        }
        createNotificationChannels(applicationContext) {
            channel("TEST", "TEST CHANNEL")
        }

        notify(applicationContext, Random.nextInt(10000), "TEST", R.drawable.ic_launcher_foreground) {
            contentTitle("LeakChecker analyse result")
            contentText("Founded ${result.leaksFound} leaks, see now!")
            priority(NotificationCompat.PRIORITY_HIGH)
        }
        return Result.success()
    }
}
