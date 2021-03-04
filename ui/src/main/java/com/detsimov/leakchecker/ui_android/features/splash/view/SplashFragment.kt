package com.detsimov.leakchecker.ui_android.features.splash.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.work.*
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.leakchecker.ui_android.BuildConfig
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.features.splash.vm.SplashViewModel
import com.detsimov.leakchecker.ui_android.navigation.CiceroneQualifier
import com.detsimov.leakchecker.ui_android.navigation.MainCicerone
import com.detsimov.leakchecker.ui_android.navigation.router
import com.detsimov.leakchecker.ui_android.workers.SecureWorker
import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {


    override val viewModel: SplashViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpWorker()
    }

    private fun setUpWorker() {
        WorkManager.getInstance(requireContext().applicationContext)
            .enqueueUniquePeriodicWork(
                SecureWorker.TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                PeriodicWorkRequestBuilder<SecureWorker>(
                    26,
                    TimeUnit.HOURS,
                    2,
                    TimeUnit.HOURS
                ).apply {
                    setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS
                    )
                    setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )
                }.build()
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTitle()
    }

    private fun setUpTitle(){
        requireView().findViewById<TextView>(R.id.tvAppName).text = getString(R.string.splash_title, BuildConfig.VERSION_NAME)
    }

    companion object {

        fun create() = SplashFragment()
    }


}