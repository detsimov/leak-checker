package com.detsimov.leakchecker.domain.interactors

import android.util.Log
import com.detsimov.leakchecker.domain.interactors.i.ISecureInteractor
import com.detsimov.leakchecker.domain.models.ScanDataModel
import com.detsimov.leakchecker.domain.repositories.ILeakRepository
import com.detsimov.leakchecker.domain.repositories.ITrackDataRepository
import com.detsimov.leakchecker.domain.repositories.UpdateParams
import com.detsimov.leakchecker.domain.service.ISecure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SecureInteractor(
    private val trackDataRepository: ITrackDataRepository,
    private val leakRepository: ILeakRepository,
    private val secure: ISecure,
) : ISecureInteractor {

    override suspend fun fullScan(): ScanDataModel = withContext(Dispatchers.IO) {
        val trackData = trackDataRepository.fetch().filter { it.isCanScan() }


        val leaks = leakRepository.fetch()

        val foundedLeaks = secure.scanLeaks(trackData to leaks).onEach {
            leakRepository.add(it)
        }

        trackData.forEach { trackDataRepository.update(UpdateParams.LastCheck(System.currentTimeMillis(), it.id)) }
        ScanDataModel(foundedLeaks.size)
    }
}