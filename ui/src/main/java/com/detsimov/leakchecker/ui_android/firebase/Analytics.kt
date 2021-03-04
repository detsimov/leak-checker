package com.detsimov.leakchecker.ui_android.firebase

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

object Analytics {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var firebaseCrashlytics: FirebaseCrashlytics

    @SuppressLint("MissingPermission")
    fun init(applicationContext: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)
        firebaseCrashlytics = FirebaseCrashlytics.getInstance()
    }

    fun sendEvent(event: EVENT, vararg args: Pair<String, Any?>){
        firebaseAnalytics.logEvent(event.name, bundleOf(*args))
    }

    fun recordException(any: Throwable){
        firebaseCrashlytics.recordException(any)
    }
}