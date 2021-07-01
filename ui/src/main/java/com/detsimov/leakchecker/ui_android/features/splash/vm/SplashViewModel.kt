package com.detsimov.leakchecker.ui_android.features.splash.vm

import android.net.Uri
import com.detsimov.core_ui.livedata.SingleLiveData
import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.interactors.i.IAuthInteractor
import com.detsimov.leakchecker.domain.repositories.IUserInfoRepository
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.detsimov.leakchecker.ui_android.firebase.EVENT
import com.detsimov.leakchecker.ui_android.navigation.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.File

class SplashViewModel(
    private val userInfoRepository: IUserInfoRepository,
    private val authInteractor: IAuthInteractor,
    private val mainRouter: Router
) : BaseViewModel() {

    private val _showConsent = SingleLiveData<Unit>()
    val showConsent = _showConsent.asLiveData()

    private val _initLibraries = SingleLiveData<Unit>()
    val initLibraries = _initLibraries.asLiveData()

    private var isRequestedConsent = false

    init {
        if (userInfoRepository.isAcceptedConsent) {
            _initLibraries.call()
        } else {
            isRequestedConsent = true
            _showConsent.call()
        }
    }

    fun onConsentAccepted() {
        userInfoRepository.isAcceptedConsent = true
        _initLibraries.call()
    }

    fun onLibrariesInitialized() {
        startAuthenticate()
    }

    fun onExit() {
        if(isRequestedConsent && userInfoRepository.isAcceptedConsent.not()) {
            Analytics.sendEvent(EVENT.USER_NOT_ACCEPT_CONSENT)
        }
    }

    private fun startAuthenticate() {
        launch {
            try {
                delay(AUTH_DELAY)
                authInteractor.authenticate()
            } catch (any: Throwable) {
                Analytics.recordException(any)
            }
            mainRouter.replaceScreen(Screens.MainNavigation())
        }
    }

    companion object {

        private const val AUTH_DELAY = 1200L
    }

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        Analytics.recordException(throwable)
    }
}