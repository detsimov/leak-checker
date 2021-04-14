package com.detsimov.leakchecker.domain.interactors

import com.detsimov.leakchecker.domain.interactors.i.ITrackDataInteractor
import com.detsimov.leakchecker.domain.interactors.i.TrackDataOverflowException
import com.detsimov.leakchecker.domain.interactors.i.TrackDataOwnListIsNotInitializedException
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.domain.repositories.IRuleRepository
import com.detsimov.leakchecker.domain.repositories.ITrackDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

internal class TrackDataInteractor(
    private val trackDataRepository: ITrackDataRepository,
    private val ruleRepository: IRuleRepository
) : ITrackDataInteractor {

    override val ownDataFlow: Flow<List<TrackDataModel>> = trackDataRepository.ownDataFlow

    override suspend fun isOwnDataInitialized(): Boolean = trackDataRepository.ownDataFlow.firstOrNull() != null

    override suspend fun isCanAdd(): Boolean {
        return trackDataRepository.ownDataFlow.firstOrNull()?.let { ownDataList ->
            ownDataList.count() < TrackDataModel.MAX_COUNT
        } ?: throw TrackDataOwnListIsNotInitializedException()
    }

    override suspend fun add(trackData: String, trackType: TrackDataModel.TypeValue) {
        withContext(Dispatchers.IO) {
            check(trackData, trackType)
            if (isCanAdd()) trackDataRepository.add(trackData, trackType)
            else throw TrackDataOverflowException()
        }
    }

    override suspend fun delete(trackDataModel: TrackDataModel) = withContext(Dispatchers.IO) {
        trackDataRepository.delete(trackDataModel)
    }

    override suspend fun clear() = withContext(Dispatchers.IO) {
        trackDataRepository.clear()
    }

    override suspend fun check(trackData: String, trackType: TrackDataModel.TypeValue) =
        if (trackType == TrackDataModel.TypeValue.PHONE) ruleRepository.checkRuPhoneNumber(trackData)
        else ruleRepository.checkEmail(trackData)
}