package com.detsimov.leakchecker.data_local.repositories


import com.detsimov.leakchecker.data_local.Database
import com.detsimov.leakchecker.data_local.mappers.trackDataMapper
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.domain.repositories.ITrackDataRepository
import com.detsimov.leakchecker.domain.repositories.UpdateParams
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow


internal class TrackDataRepository(database: Database) : ITrackDataRepository {


    private val queries = database.trackDataEntityQueries

    override val ownDataFlow: Flow<List<TrackDataModel>> = queries.all(trackDataMapper).asFlow().mapToList()


    override suspend fun add(data: String, trackType: TrackDataModel.TypeValue) =
        queries.add(data, trackType, TrackDataModel.Period.DEFAULT)


    override suspend fun fetch(): List<TrackDataModel> =
        queries.all(trackDataMapper).executeAsList()

    override suspend fun update(updateParams: UpdateParams) {
        when (updateParams) {
            is UpdateParams.LastCheck -> queries.updateLastCheck(updateParams.value, updateParams.id)
        }
    }

    override suspend fun delete(trackDataModel: TrackDataModel) {
        queries.delete(trackDataModel.id)
    }


    override suspend fun clear() {
        queries.clear()
    }


}