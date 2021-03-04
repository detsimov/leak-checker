package com.detsimov.leakchecker.ui_android.features.leak.master

import androidx.lifecycle.MutableLiveData
import com.detsimov.core_ui.livedata.asLiveData
import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.interactors.i.ILeakInteractor
import com.detsimov.leakchecker.domain.models.*
import com.detsimov.leakchecker.ui_android.features.leak.item.LeakItem
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LeakMasterViewModel(private val leakInteractor: ILeakInteractor) : BaseViewModel() {

    private val _leaks = MutableLiveData<List<LeakItem>>()
    val leaks = _leaks.asLiveData()

    init {
       onGetLeaks()
    }

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        Analytics.recordException(throwable)
    }

    private fun onGetLeaks(){
        launch {
            launch {
                leakInteractor.ownDataFlow
                    .collect { data ->
                        data.loading { _progress.value = true }
                            .successData { _progress.value = false
                                _leaks.value = it.map { model -> model.mapToItem() }
                            }
                            .error { handleError(it) }
                    }
            }
            launch {
                leakInteractor.refreshOwn()
            }
        }
    }

    fun onRefreshLeaks() {
        launch {
            leakInteractor.refreshOwn()
        }
    }

    private fun LeakModel.mapToItem() =
        LeakItem(this@mapToItem)


}