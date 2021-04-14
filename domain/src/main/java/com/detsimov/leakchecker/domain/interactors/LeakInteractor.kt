package com.detsimov.leakchecker.domain.interactors

import com.detsimov.leakchecker.domain.interactors.i.ILeakInteractor
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.repositories.ILeakRepository
import kotlinx.coroutines.flow.Flow

internal class LeakInteractor(leakRepository: ILeakRepository) : ILeakInteractor {

    override val ownDataFlow: Flow<List<LeakModel>> = leakRepository.ownDataFlow
}