package com.detsimov.leakchecker.ui_android.features.settings.master

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import com.detsimov.core_ui.livedata.SingleLiveData
import com.detsimov.core_ui.livedata.asLiveData
import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.repositories.ITokenRepository
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import kotlinx.coroutines.launch

class SettingsMasterViewModel(private val tokenRepository: ITokenRepository) : BaseViewModel() {


    private val _token = MutableLiveData<String?>()
    val token = _token.asLiveData()

    private val _copyClipData = SingleLiveData<ClipData>()
    val copyClipData = _copyClipData.asLiveData()

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        Analytics.recordException(throwable)
    }

    init {
        launch {
            _token.value = tokenRepository.get()
        }
    }

    fun onCopyText(clipData: ClipData){
        _copyClipData.value = clipData
    }

}