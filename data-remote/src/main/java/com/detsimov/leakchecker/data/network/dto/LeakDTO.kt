package com.detsimov.leakchecker.data.network.dto

import com.detsimov.core_data.RequestFactory
import com.detsimov.core_data.ResponseMapper
import com.detsimov.leakchecker.data.network.dto.TrackDataDTO.Response.Read.Companion.transform
import com.detsimov.leakchecker.domain.models.LeakModel
import kotlinx.serialization.Serializable

internal class LeakDTO {

    class Response {
        @Serializable
        data class Read(
            val id: Long,
            val track: TrackDataDTO.Response.Read,
            val line: String,
            val lastBreach: String?,
            val source: String?,
            val discoveryDate: Long,
        ) {
            companion object : ResponseMapper<Read, LeakModel>() {
                override fun Read.transform(): LeakModel =
                    LeakModel(id, line, lastBreach, source, track.transform())
            }
        }
    }

    class Request {
        @Serializable
        data class Delete(
            val id: Long
        ) {
            companion object : RequestFactory<Delete, LeakModel>() {
                override fun from(model: LeakModel): Delete =
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

}