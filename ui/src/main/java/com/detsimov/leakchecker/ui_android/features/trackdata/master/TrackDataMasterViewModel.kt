package com.detsimov.leakchecker.ui_android.features.trackdata.master

import androidx.lifecycle.MutableLiveData
import com.detsimov.core_domain.handleErrors
import com.detsimov.core_ui.livedata.SingleLiveData
import com.detsimov.core_ui.livedata.asLiveData
import com.detsimov.core_ui.viewmodel.BaseViewModel
import com.detsimov.leakchecker.domain.interactors.i.ISecureInteractor
import com.detsimov.leakchecker.domain.interactors.i.ITrackDataInteractor
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.ui_android.features.trackdata.master.items.TrackDataItem
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.detsimov.leakchecker.ui_android.firebase.EVENT
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TrackDataMasterViewModel(private val trackDataInteractor: ITrackDataInteractor, private val secureInteractor: ISecureInteractor) :
    BaseViewModel() {

    private val _progressSaveTrackData = MutableLiveData<Boolean>()
    val progressSaveTrackData = _progressSaveTrackData.asLiveData()

    private val _showTrackDataCreateDialog = SingleLiveData<Unit>()
    val showTrackDataCreateDialog = _showTrackDataCreateDialog.asLiveData()

    private val _showTrackDataDeleteAllDialog = SingleLiveData<Unit>()
    val showTrackDataDeleteAllDialog = _showTrackDataDeleteAllDialog.asLiveData()

    private val _showTrackDataDeleteDialog = SingleLiveData<TrackDataModel>()
    val showTrackDataDeleteDialog = _showTrackDataDeleteDialog.asLiveData()

    private val _trackData = MutableLiveData<List<TrackDataItem>>()
    val trackData = _trackData.asLiveData()

    private val _clearTrackData = SingleLiveData<Unit>()
    val clearTrackData = _clearTrackData.asLiveData()

    init {
        subscribeToTrackData()
    }

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        Analytics.recordException(throwable)
    }

    fun onSaveTrackData(data: String, trackType: TrackDataModel.TypeValue) {
        launch {
            try {
                _progressSaveTrackData.value = true
                trackDataInteractor.add(data, trackType)
                _progressSaveTrackData.value = false
                Analytics.sendEvent(EVENT.USER_ADD_TRACK_DATA_SUCCESS)
            } catch (any: Throwable) {
                _progressSaveTrackData.value = false
                handleError(any)
            }
        }
    }

    private fun subscribeToTrackData() {
        trackDataInteractor.ownDataFlow
            .onStart { _progress.value = true }
            .map { array -> array.map { TrackDataItem(it) } }
            .onEach {
                _trackData.value = it
                _progress.value = false
            }
            .handleErrors { handleError(it) }
            .launchIn(this)
    }

    fun onShowTrackDataDeleteAllDialog() {
        _showTrackDataDeleteAllDialog.call()
    }

    fun onShowTrackDataDeleteDialog(trackData: TrackDataModel) {
        _showTrackDataDeleteDialog.value = trackData
    }

    fun onDeleteAllTrackData() {
        launch {
            trackDataInteractor.clear()
            _clearTrackData.call()
        }
    }

    fun onDeleteTrackData(trackDataModel: TrackDataModel) {
        launch {
            trackDataInteractor.delete(trackDataModel)
        }
    }

    fun onShowTrackDataCreateDialog() {
        Analytics.sendEvent(EVENT.USER_ADD_TRACK_DATA_CLICK)
        launch {
            if (trackDataInteractor.isOwnDataInitialized())
                _showTrackDataCreateDialog.call()
        }
    }
}