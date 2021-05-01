package com.detsimov.leakchecker.ui_android.firebase

import android.annotation.SuppressLint
import android.app.Application
import androidx.core.os.bundleOf
import com.detsimov.leakchecker.ui_android.AppProcess
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

object Analytics {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var firebaseCrashlytics: FirebaseCrashlytics

    private const val APPMETRICA_API_KEY = "ad2e3ba5-9151-4189-8b26-f8a653064639"

    private val yandexMetricaConfig = YandexMetricaConfig.newConfigBuilder(APPMETRICA_API_KEY).build()

    @SuppressLint("MissingPermission")
    fun init(application: Application) {
        val applicationContext = application.applicationContext
        if (AppProcess.isUndefined().not()) {
            firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)
            firebaseCrashlytics = FirebaseCrashlytics.getInstance()
        }
        YandexMetrica.activate(applicationContext, yandexMetricaConfig)
        YandexMetrica.enableActivityAutoTracking(application)
    }

    fun sendEvent(event: EVENT, vararg args: Pair<String, Any?>) {
        firebaseAnalytics.logEvent(event.name, bundleOf(*args))
        YandexMetrica.reportEvent(event.name, args.toMap())
    }

    fun recordException(any: Throwable) {
        firebaseCrashlytics.recordException(any)
        YandexMetrica.reportUnhandledException(any)
    }

}