package com.detsimov.leakchecker.ui_android.features.main_navigation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.detsimov.leakchecker.ui_android.BuildConfig
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.FragmentMainNavigationBinding
import com.detsimov.leakchecker.ui_android.features.main_navigation.adapter.ViewPagerAdapter
import com.detsimov.leakchecker.ui_android.features.stack.i.StackInitFragmentFactory
import com.detsimov.leakchecker.ui_android.features.stack.models.StackScreen
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.detsimov.leakchecker.ui_android.firebase.EVENT
import com.detsimov.leakchecker.ui_android.navigation.Screens
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError

class MainNavigationFragment : Fragment(R.layout.fragment_main_navigation),
    StackInitFragmentFactory {

    private val viewBinding by viewBinding(FragmentMainNavigationBinding::bind)

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewPager()
        setUpBottomNavigation()
        setUpBanner()
    }

    private fun setUpBanner() {
        if (BuildConfig.DEBUG.not()) viewBinding.adView.apply {
            loadAd(AdRequest.Builder().build())
            adListener = object : AdListener() {
                override fun onAdLoaded() {
                    visibility = View.VISIBLE
                    Analytics.sendEvent(EVENT.AD_LOADED_BANNER)
                }

                override fun onAdClicked() {
                    Analytics.sendEvent(EVENT.AD_CLICK_BANNER)
                }

                override fun onAdClosed() {
                    Analytics.sendEvent(EVENT.AD_CLOSE_BANNER)
                }

                override fun onAdFailedToLoad(error: LoadAdError?) {
                    Analytics.sendEvent(EVENT.AD_ERROR_BANNER, "error_code" to error?.code)
                }
            }
        }
    }

    private fun setUpViewPager() {
        viewBinding.viewPager.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = viewPagerAdapter.itemCount
            isUserInputEnabled = false
        }
    }

    private fun setUpBottomNavigation() {
        viewBinding.apply {
            bottomNavigationView.setOnNavigationItemSelectedListener {
                val screen = when (it.itemId) {
                    R.id.btnTracker -> StackScreen.TRACK
                    R.id.btnLeak -> StackScreen.LEAK
                    R.id.btnSettings -> StackScreen.SETTINGS
                    else -> error("Unknown item id")
                }
                viewPager.setStackItem(screen)
                true
            }
            bottomNavigationView.selectedItemId = R.id.btnTracker
        }
    }

    override fun onInitFragment(stackScreen: StackScreen): FragmentScreen = when (stackScreen) {
        StackScreen.LEAK -> Screens.LeakMaster()
        StackScreen.TRACK -> Screens.TrackDataMaster()
        StackScreen.SETTINGS -> Screens.SettingsMaster()
    }

    companion object {

        fun create() = MainNavigationFragment()
    }
}

private fun ViewPager2.setStackItem(stackScreen: StackScreen) {
    setCurrentItem(StackScreen.values().indexOf(stackScreen), false)
}