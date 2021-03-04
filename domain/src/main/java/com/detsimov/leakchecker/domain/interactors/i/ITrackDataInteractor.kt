package com.detsimov.leakchecker.domain.interactors.i

import com.detsimov.core_domain.BaseInteractor
import com.detsimov.leakchecker.domain.exceptions.RuleException
import com.detsimov.leakchecker.domain.exceptions.TrackDataException
import com.detsimov.leakchecker.domain.models.TrackDataModel
import kotlinx.coroutines.flow.Flow

interface ITrackDataInteractor : BaseInteractor {

    val ownDataFlow: Flow<List<TrackDataModel>>

    suspend fun isOwnDataInitialized(): Boolean

    @Throws(TrackDataOwnListIsNotInitializedException::class)
    suspend fun isCanAdd(): Boolean

    @Throws(TrackDataOverflowException::class, TrackDataOwnListIsNotInitializedException::class, RuleException::class)
    suspend fun add(trackData: String, trackType: TrackDataModel.TypeValue)

    suspend fun delete(trackDataModel: TrackDataModel)

    suspend fun clear()

    suspend fun check(trackData: String, trackType: TrackDataModel.TypeValue)

}

class TrackDataOwnListIsNotInitializedException : TrackDataException()

class TrackDataOverflowException : TrackDataException()
