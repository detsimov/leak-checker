package com.detsimov.leakchecker.data_local.repositories

import com.detsimov.core_domain.models.DataStatus
import com.detsimov.leakchecker.data_local.Database
import com.detsimov.leakchecker.data_local.mappers.leaksMapper
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.repositories.FetchParams
import com.detsimov.leakchecker.domain.repositories.ILeakRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class LeakRepository(database: Database) : ILeakRepository {

    private val queries = database.leakEntityQueries

    override val ownDataFlow: StateFlow<DataStatus<List<LeakModel>, Throwable>> =
        MutableStateFlow(DataStatus.Empty)

    override suspend fun refreshOwn() {

    }

    override suspend fun add(scanned: LeakModel.Scanned) =
        queries.add(scanned.data, scanned.lastBreach, scanned.source, scanned.fromLine, scanned.fromLineType)


    override suspend fun delete(leakModel: LeakModel) =
        queries.delete(leakModel.id)


    override suspend fun clear() =
        queries.clear()


    override suspend fun fetch(fetchParams: FetchParams): List<LeakModel> =
        queries.all(leaksMapper).executeAsList()


}