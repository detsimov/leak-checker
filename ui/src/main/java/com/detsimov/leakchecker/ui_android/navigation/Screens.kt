package com.detsimov.leakchecker.ui_android.navigation

import com.detsimov.leakchecker.ui_android.features.leak.master.LeakMasterFragment
import com.detsimov.leakchecker.ui_android.features.main_navigation.view.MainNavigationFragment
import com.detsimov.leakchecker.ui_android.features.settings.master.SettingsMasterFragment
import com.detsimov.leakchecker.ui_android.features.splash.view.SplashFragment
import com.detsimov.leakchecker.ui_android.features.trackdata.master.TrackDataMasterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun MainNavigation() = FragmentScreen {
        MainNavigationFragment.create()
    }

    fun Splash() = FragmentScreen {
        SplashFragment.create()
    }

    fun TrackDataMaster() = FragmentScreen {
        TrackDataMasterFragment.create()
    }

    fun LeakMaster() = FragmentScreen {
        LeakMasterFragment.create()
    }

    fun SettingsMaster() = FragmentScreen {
        SettingsMasterFragment.create()
    }
}