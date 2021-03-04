package com.detsimov.leakchecker.ui_android.navigation

import com.detsimov.leakchecker.ui_android.features.leak.master.LeakMasterFragment
import com.detsimov.leakchecker.ui_android.features.main_navigation.view.MainNavigationFragment
import com.detsimov.leakchecker.ui_android.features.settings.master.SettingsMasterFragment
import com.detsimov.leakchecker.ui_android.features.splash.view.SplashFragment
import com.detsimov.leakchecker.ui_android.features.trackdata.master.TrackDataMasterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun mainNavigation() =
        FragmentScreen { MainNavigationFragment.create() }

    fun splash() =
        FragmentScreen { SplashFragment.create() }

    fun trackDataMaster(tag: String = TrackDataMasterFragment.TAG) =
        FragmentScreen(tag) { TrackDataMasterFragment.create() }

    fun leakMaster(tag: String = LeakMasterFragment.TAG) =
        FragmentScreen(tag) { LeakMasterFragment.create() }

    fun settingsMaster(tag: String = SettingsMasterFragment.TAG) =
        FragmentScreen(tag) { SettingsMasterFragment.create() }

}