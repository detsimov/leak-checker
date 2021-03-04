package com.detsimov.leakchecker.domain.repositories

import com.detsimov.core_domain.models.DataStatus
import com.detsimov.leakchecker.domain.models.LeakModel
import kotlinx.coroutines.flow.Flow

interface ILeakRepository {

    val ownDataFlow: Flow<DataStatus<List<LeakModel>, Throwable>>

    suspend fun refreshOwn()

    suspend fun add(scanned: LeakModel.Scanned)

    suspend fun delete(leakModel: LeakModel)

    suspend fun clear()

    suspend fun fetch(fetchParams: FetchParams = FetchParams.All) : List<LeakModel>

}


sealed class FetchParams {

    object All : FetchParams()

}
