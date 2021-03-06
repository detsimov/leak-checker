package com.detsimov.leakchecker.domain.interactors.i

import com.detsimov.core_domain.BaseInteractor
import com.detsimov.core_domain.models.DataStatus
import com.detsimov.leakchecker.domain.models.LeakModel
import kotlinx.coroutines.flow.Flow

interface ILeakInteractor : BaseInteractor {

    val ownDataFlow: Flow<List<LeakModel>>

}
