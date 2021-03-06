package com.detsimov.leakchecker.domain.interactors

import com.detsimov.core_domain.models.DataStatus
import com.detsimov.leakchecker.domain.interactors.i.ILeakInteractor
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.repositories.ILeakRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class LeakInteractor(private val leakRepository: ILeakRepository) : ILeakInteractor {

    override val ownDataFlow: Flow<List<LeakModel>> = leakRepository.ownDataFlow


}