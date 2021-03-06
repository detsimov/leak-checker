package com.detsimov.leakchecker.ui_android.features.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.ActivityMainBinding
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.detsimov.leakchecker.ui_android.firebase.EVENT
import com.detsimov.leakchecker.ui_android.navigation.MainCicerone
import com.detsimov.leakchecker.ui_android.navigation.Screens
import com.detsimov.leakchecker.ui_android.navigation.cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cicerone = cicerone<Router>(MainCicerone)
    private val appNavigator =  AppNavigator(
        this@MainActivity,
        R.id.fragmentContainerView
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setUpCicerone()
        setUpIsFromNotification()
    }


    private fun setUpCicerone() {
        cicerone.apply {
            router.replaceScreen(Screens.splash())
        }
    }


    private fun setUpIsFromNotification(){
        if(intent.getBooleanExtra(EXTRA_IS_FROM_NOTIFICATION, false)) Analytics.sendEvent(EVENT.USER_CLICK_ON_ANALYSE_SCAN)
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(appNavigator)

    }

    override fun onPause() {
        super.onPause()
        cicerone.getNavigatorHolder().removeNavigator()
    }


    companion object {

        private const val EXTRA_IS_FROM_NOTIFICATION = "IS_FROM_NOTIFICATION"

        fun intent(context: Context, isFromNotification: Boolean = false) = Intent(context, MainActivity::class.java).apply {
            putExtra(EXTRA_IS_FROM_NOTIFICATION, isFromNotification)
        }
    }


}




