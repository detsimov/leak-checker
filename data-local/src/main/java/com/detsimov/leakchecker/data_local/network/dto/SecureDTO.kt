package com.detsimov.leakchecker.data_local.network.dto

import com.detsimov.core_data.RequestFactory
import com.detsimov.core_data.ResponseMapper
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.models.TrackDataModel
import kotlinx.serialization.Serializable

internal class SecureDTO {

    class Request {
        @Serializable
        data class Scan(val trackData: List<TrackData>, val ignore: List<Leak>?) {

            companion object : RequestFactory<Pair<List<TrackDataModel>, List<LeakModel>?>, Scan> {
                override fun from(model: Pair<List<TrackDataModel>, List<LeakModel>?>): Scan = model.run {
                    Scan(first.map {
                        TrackData(it.id, it.value, it.type)
                    }, second?.map {
                        Leak(it.data, it.source, it.lastBreach)
                    })
                }


                @Serializable
                data class TrackData constructor(
                    val id: Long,
                    val line: String,
                    val type: TrackDataModel.TypeValue
                )

                @Serializable
                data class Leak constructor(
                    val line: String,
                    val source: String?,
                    val dateFoundedMillis: String?
                )


            }
        }

    }


    class Response {

        @Serializable
        data class Scan(val leaks: List<Leak>) {

            companion object : ResponseMapper<Scan, List<LeakModel.Scanned>> {

                override fun Scan.transform(): List<LeakModel.Scanned> = leaks.map {
                    LeakModel.Scanned(
                        it.line,
                        it.dateFoundedMillis,
                        it.source,
                        it.fromLine,
                        it.fromLineType
                    )
                }

            }

            @Serializable
            data class Leak constructor(
                val line: String,
                val source: String?,
                val dateFoundedMillis: String?,
                val fromLine: String,
                val fromLineType: TrackDataModel.TypeValue
            )
        }
    }
}