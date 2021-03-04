package com.detsimov.leakchecker.data.network.dto

import com.detsimov.core_data.RequestFactory
import com.detsimov.core_data.ResponseMapper
import com.detsimov.leakchecker.data.utils.SimpleDateFormatFactory
import com.detsimov.leakchecker.domain.models.TrackDataModel
import kotlinx.serialization.Serializable
import java.util.*

internal class TrackDataDTO {

    class Request {
        @Serializable
        data class Create(
            val type: TrackDataModel.TypeValue,
            val data: String
        )

        @Serializable
        data class Delete(
            val id: Long
        ) {
            companion object : RequestFactory<Delete, TrackDataModel>() {
                override fun from(model: TrackDataModel): Delete =
                    Delete(model.id)
            }
        }

        @Serializable
        data class DeleteAll private constructor(val id: Int? = null) {
            companion object {
                fun create() = DeleteAll(null)
            }
        }
    }

    class Response {
        @Serializable
        data class Read(
            val id: Long,
            val data: String,
            val type: TrackDataModel.TypeValue,
            val period: TrackDataModel.Period,
            val lastCheck: Long?
        ) {
            companion object : ResponseMapper<Read, TrackDataModel>() {
                override fun Read.transform(): TrackDataModel =
                    TrackDataModel(
                        id,
                        data,
                        type,
                        period,
                        if (lastCheck != null) SimpleDateFormatFactory.create().format(
                            Date(lastCheck)
                        ) else null
                    )
            }
        }
    }
}