package com.detsimov.leakchecker.data_local.repositories

import com.detsimov.core_domain.models.DataStatus
import com.detsimov.leakchecker.data_local.Database
import com.detsimov.leakchecker.data_local.mappers.leaksMapper
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.repositories.FetchParams
import com.detsimov.leakchecker.domain.repositories.ILeakRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

internal class LeakRepository(database: Database) : ILeakRepository {

    private val queries = database.leakEntityQueries

    override val ownDataFlow: Flow<List<LeakModel>> = queries.all(leaksMapper).asFlow().mapToList()

    override suspend fun add(scanned: LeakModel.Scanned) =
        queries.add(scanned.data, scanned.lastBreach, scanned.source, scanned.fromLine, scanned.fromLineType)


    override suspend fun delete(leakModel: LeakModel) =
        queries.delete(leakModel.id)


    override suspend fun clear() =
        queries.clear()


    override suspend fun fetch(fetchParams: FetchParams): List<LeakModel> =
        queries.all(leaksMapper).executeAsList()


}