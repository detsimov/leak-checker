package com.detsimov.leakchecker.ui_android.features.leak.master

import androidx.lifecycle.MutableLiveData
import com.detsimov.core_domain.handleErrors
import com.detsimov.core_ui.livedata.asLiveData
import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.interactors.i.ILeakInteractor
import com.detsimov.leakchecker.ui_android.features.leak.item.LeakItem
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LeakMasterViewModel(private val leakInteractor: ILeakInteractor) : BaseViewModel() {

    private val _leaks = MutableLiveData<List<LeakItem>>()
    val leaks = _leaks.asLiveData()

    init {
        subscribeToLeaks()
    }

    private fun subscribeToLeaks() {
        launch {
            leakInteractor.ownDataFlow
                .map { array -> array.map { LeakItem(it) } }
                .onStart { _progress.value = true }
                .catch { handleError(it) }
                .collect {
                    _leaks.value = it
                    _progress.value = false
                }
        }
    }

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        Analytics.recordException(throwable)
    }
}
