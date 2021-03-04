package com.detsimov.leakchecker.ui_android.features.trackdata.creator.viewmodel

import com.detsimov.core_ui.livedata.SingleLiveData
import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.interactors.i.ITrackDataInteractor
import com.detsimov.leakchecker.ui_android.features.trackdata.creator.models.TrackDataCreateUi
import kotlinx.coroutines.launch

class TrackDataCreateViewModel(private val trackDataInteractor: ITrackDataInteractor) :
    BaseViewModel() {

    private val _closePressed = SingleLiveData<Unit>()
    val closePressed = _closePressed.asLiveData()

    private val _onSaveTrackData = SingleLiveData<TrackDataCreateUi>()
    val onSaveTrackData = _onSaveTrackData.asLiveData()

    fun onClosePressed() {
        _closePressed.call()
    }

    fun onCheckTrackData(trackDataCreateUi: TrackDataCreateUi) {
        launch {
            trackDataInteractor.check(trackDataCreateUi.value, trackDataCreateUi.type)
            _onSaveTrackData.value = trackDataCreateUi
            _closePressed.call()
        }
    }

}

