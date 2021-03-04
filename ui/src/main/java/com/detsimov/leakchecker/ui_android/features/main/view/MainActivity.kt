package com.detsimov.leakchecker.ui_android.features.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.ActivityMainBinding
import com.detsimov.leakchecker.ui_android.navigation.MainCicerone
import com.detsimov.leakchecker.ui_android.navigation.Screens
import com.detsimov.leakchecker.ui_android.navigation.cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cicerone = cicerone<Router>(MainCicerone)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setUpCicerone()
    }


    private fun setUpCicerone() {
        cicerone.apply {
            getNavigatorHolder().setNavigator(
                AppNavigator(
                    this@MainActivity,
                    R.id.fragmentContainerView
                )
            )
            router.replaceScreen(Screens.splash())
        }
    }


    companion object {

        fun intent(context: Context) = Intent(context, MainActivity::class.java)
    }


}




