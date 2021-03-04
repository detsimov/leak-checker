package com.detsimov.leakchecker.ui_android.features.splash.vm

import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.interactors.i.IAuthInteractor
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.detsimov.leakchecker.ui_android.navigation.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authInteractor: IAuthInteractor,
    private val mainRouter: Router
) : BaseViewModel() {

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        Analytics.recordException(throwable)
    }

    init {
        launch {
            authInteractor.authenticate()
            mainRouter.replaceScreen(Screens.mainNavigation())
        }
    }

}