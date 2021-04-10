package com.detsimov.leakchecker.ui_android.workers

import android.content.Context
import androidx.work.*
import com.detsimov.leakchecker.domain.interactors.i.ISecureInteractor
import com.detsimov.leakchecker.ui_android.common.inject
import com.detsimov.leakchecker.ui_android.notifications.NotificationUtil
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class SecureWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object Manager {

        private const val TAG = "SecureWorker"
        private const val REPEAT_INTERVAL_HOURS = 4L
        private const val FLEX_INTERVAL_HOURS = 2L

        fun start(applicationContext: Context) {
            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                PeriodicWorkRequestBuilder<SecureWorker>(
                    REPEAT_INTERVAL_HOURS,
                    TimeUnit.HOURS,
                    FLEX_INTERVAL_HOURS,
                    TimeUnit.HOURS
                ).apply {
                    setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS
                    )
                    setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )
                }.build()
            )
        }
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
        if (result.leaksFound > 0) NotificationUtil.showAnalyseScanNotification(result)
        return Result.success()
    }
}
