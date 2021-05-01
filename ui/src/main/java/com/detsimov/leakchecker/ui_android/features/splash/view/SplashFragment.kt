package com.detsimov.leakchecker.ui_android.features.splash.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.leakchecker.ui_android.BuildConfig
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.common.AlertDialogFactory
import com.detsimov.leakchecker.ui_android.features.splash.vm.SplashViewModel
import com.detsimov.leakchecker.ui_android.workers.SecureWorker
import com.google.android.gms.ads.MobileAds
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()

    private val consentDialog by lazy {
        AlertDialogFactory.linkAlertDialog(requireContext(), R.string.splash_consent_message_dialog_consent)
            .apply {
                setCancelable(false)
                setPositiveButton(R.string.splash_consent_btn_ok_dialog_consent) { _, _ ->
                    viewModel.onConsentAccepted()
                }
            }.create()
    }

    private val applicationContext
        get() = requireActivity().applicationContext

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTitle()
        with(viewModel) {
            showConsent.observe(viewLifecycleOwner) {
                consentDialog.show()
            }
            initLibraries.observe(viewLifecycleOwner) {
                MobileAds.initialize(applicationContext)
                SecureWorker.start(requireContext().applicationContext)
                viewModel.onLibrariesInitialized()
            }
        }
    }

    override fun onDestroy() {
        viewModel.onExit()
        super.onDestroy()
    }

    private fun setUpTitle() {
        requireView().findViewById<TextView>(R.id.tvAppName).text =
            getString(R.string.splash_title, BuildConfig.VERSION_NAME)
    }

    companion object {

        fun create() = SplashFragment()
    }
}