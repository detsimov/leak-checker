package com.detsimov.leakchecker.domain.models

data class LeakModel(
    val id: Long,
    val data: String,
    val lastBreach: String?,
    val source: String?,
    val fromLine: String,
    val fromLineType: TrackDataModel.TypeValue
) {

    data class Scanned(
        val data: String,
        val lastBreach: String?,
        val source: String?,
        val fromLine: String,
        val fromLineType: TrackDataModel.TypeValue
    )

}