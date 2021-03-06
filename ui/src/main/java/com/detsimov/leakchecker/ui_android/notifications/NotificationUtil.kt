package com.detsimov.leakchecker.ui_android.notifications

import android.content.Context
import androidx.core.app.NotificationCompat
import com.detsimov.leakchecker.domain.models.ScanDataModel
import com.detsimov.leakchecker.ui_android.R
import com.kirich1409.androidnotificationdsl.channels.createNotificationChannels
import com.kirich1409.androidnotificationdsl.notify
import kotlin.random.Random

object NotificationUtil {

    private lateinit var applicationContext: Context

    /** Analyse tags */
    private val CHANNEL_ID_ANALYSE_SCAN = R.string.notification_channel_id_analyse_scan
    private val CHANNEL_NAME_ANALYSE_SCAN = R.string.notification_channel_name_analyse_scan


    fun init(context: Context) {
        this.applicationContext = context
    }


    fun showAnalyseScanNotification(scanDataModel: ScanDataModel) {
        createNotificationChannels(applicationContext) {
            channel(
                applicationContext.getString(CHANNEL_ID_ANALYSE_SCAN),
                applicationContext.getString(CHANNEL_NAME_ANALYSE_SCAN)
            )
        }


        notify(applicationContext, Random.nextInt(10000), "TEST", R.drawable.ic_launcher_foreground) {
            contentTitle(applicationContext.getString(R.string.notification_title_analyse_scan))
            contentText(
                applicationContext.getString(
                    R.string.notification_content_analyse_scan,
                    scanDataModel.leaksFound.toString()
                )
            )
            priority(NotificationCompat.PRIORITY_HIGH)
        }
    }
}