package com.detsimov.leakchecker.domain.repositories

import com.detsimov.leakchecker.domain.exceptions.TrackDataException
import com.detsimov.leakchecker.domain.models.TrackDataModel
import kotlinx.coroutines.flow.Flow

interface ITrackDataRepository {

    val ownDataFlow: Flow<List<TrackDataModel>>

    suspend fun add(
        data: String,
        trackType: TrackDataModel.TypeValue
    )

    suspend fun fetch() : List<TrackDataModel>

    suspend fun update(updateParams: UpdateParams)

    @Throws(TrackDataDeleteException::class)
    suspend fun delete(trackDataModel: TrackDataModel)

    suspend fun clear()
}

sealed class UpdateParams(open val id: Long) {

   data class LastCheck(val value: Long, override val id: Long) : UpdateParams(id)
}

class TrackDataDeleteException : TrackDataException()
