package com.detsimov.leakchecker.data.repositories

import com.detsimov.core_domain.models.DataStatus
import com.detsimov.core_domain.models.dataStatusSuccessData
import com.detsimov.leakchecker.data.network.dto.TrackDataDTO
import com.detsimov.leakchecker.data.network.dto.TrackDataDTO.Response.Read.Companion.transform
import com.detsimov.leakchecker.data.network.services.TrackDataService
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.domain.repositories.ITrackDataRepository
import com.detsimov.leakchecker.domain.repositories.TrackDataDeleteException
import kotlinx.coroutines.flow.MutableStateFlow

internal class TrackDataRepository(private val trackDataService: TrackDataService) :
    ITrackDataRepository {


    override val ownDataFlow =
        MutableStateFlow<DataStatus<List<TrackDataModel>, Throwable>>(DataStatus.Empty)

    override val ownDataList: List<TrackDataModel>? by dataStatusSuccessData { ownDataFlow.value }

    override suspend fun refreshOwn() {
        ownDataFlow.value = DataStatus.Loading
        try {
            ownDataFlow.value = DataStatus.Success(trackDataService.get().map {
                it.transform()
            })
        } catch (any: Throwable) {
            ownDataFlow.value = DataStatus.Error(any)
        }
    }

    private fun updateOwnData(updateBlock: MutableList<TrackDataModel>.() -> Unit) {
        ownDataFlow.value.successData { leaks ->
            // TODO: Произойдёт хуйня если на этом этапе что-то в ownDataFlow добавится
            ownDataFlow.value = DataStatus.Success(leaks.toMutableList().apply(updateBlock))
        }
    }

    override suspend fun add(data: String, trackType: TrackDataModel.TypeValue) {
        val addedTrackData =
            trackDataService.add(TrackDataDTO.Request.Create(trackType, data)).transform()
        updateOwnData { add(0, addedTrackData) }
    }

    override suspend fun fetch(): List<TrackDataModel> {
        TODO()
    }


    override suspend fun delete(trackDataModel: TrackDataModel) {
        trackDataService.delete(TrackDataDTO.Request.Delete.from(trackDataModel)).apply {
            if (code() == 400) throw TrackDataDeleteException()
            else updateOwnData { remove(trackDataModel) }
        }
    }

    override suspend fun clear() {
        trackDataService.deleteAll(TrackDataDTO.Request.DeleteAll.create())
        updateOwnData { clear() }
    }


}