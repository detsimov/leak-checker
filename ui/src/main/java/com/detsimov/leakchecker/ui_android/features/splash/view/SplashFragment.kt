package com.detsimov.leakchecker.ui_android.features.splash.view

import android.os.Bundle
import androidx.work.*
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.features.splash.vm.SplashViewModel
import com.detsimov.leakchecker.ui_android.workers.SecureWorker
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
                    30,
                    TimeUnit.MINUTES,
                    15,
                    TimeUnit.MINUTES
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

    companion object {

        fun create() = SplashFragment()
    }


}