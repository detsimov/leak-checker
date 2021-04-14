package com.detsimov.leakchecker.ui_android.features.splash.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.leakchecker.ui_android.BuildConfig
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.features.splash.vm.SplashViewModel
import com.detsimov.leakchecker.ui_android.workers.SecureWorker
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpWorker()
    }

    private fun setUpWorker() {
        SecureWorker.start(requireContext().applicationContext)
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