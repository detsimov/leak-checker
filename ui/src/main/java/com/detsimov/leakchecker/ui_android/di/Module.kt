package com.detsimov.leakchecker.ui_android.di

import com.detsimov.leakchecker.ui_android.features.leak.master.LeakMasterViewModel
import com.detsimov.leakchecker.ui_android.features.settings.master.SettingsMasterViewModel
import com.detsimov.leakchecker.ui_android.features.splash.vm.SplashViewModel
import com.detsimov.leakchecker.ui_android.features.trackdata.creator.viewmodel.TrackDataCreateViewModel
import com.detsimov.leakchecker.ui_android.features.trackdata.master.TrackDataMasterViewModel
import com.detsimov.leakchecker.ui_android.navigation.MainCicerone
import com.detsimov.leakchecker.ui_android.navigation.cicerone
import com.detsimov.leakchecker.ui_android.navigation.router
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    /** Cicerone */
    cicerone(Router(), MainCicerone)

    /** ViewModel's */
    viewModel { SplashViewModel(get(), get(), router(MainCicerone)) }

    viewModel { TrackDataMasterViewModel(get(), get()) }
    viewModel { TrackDataCreateViewModel(get()) }

    viewModel { LeakMasterViewModel(get()) }

    viewModel { SettingsMasterViewModel(get()) }
}