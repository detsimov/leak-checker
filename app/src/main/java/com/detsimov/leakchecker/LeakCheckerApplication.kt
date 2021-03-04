package com.detsimov.leakchecker

import android.app.Application
import androidx.work.*
import com.detsimov.leakchecker.data_local.di.dataModule
import com.detsimov.leakchecker.domain.di.domainModule
import com.detsimov.leakchecker.ui_android.AppProcess
import com.detsimov.leakchecker.ui_android.Process
import com.detsimov.leakchecker.ui_android.di.uiModule
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.google.android.gms.ads.MobileAds
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LeakCheckerApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        AppProcess.init(this)
        Analytics.init(this)
        if (AppProcess.isEqual(Process.MAIN)) {
            MobileAds.initialize(this)
            startKoin {
                androidContext(this@LeakCheckerApplication)
                modules(domainModule, dataModule, uiModule)
            }
        } else if (AppProcess.isEqual(Process.REMOTE)) {
            startKoin {
                androidContext(this@LeakCheckerApplication)
                modules(domainModule)
            }
        }
    }
}