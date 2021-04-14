package com.detsimov.leakchecker.data_local.mappers

import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.domain.utils.SimpleDateFormatFactory

val trackDataMapper: (
    id: Long,
    value: String,
    type: TrackDataModel.TypeValue,
    period: TrackDataModel.Period,
    last_check: Long?,
    isActivate: Boolean?
) -> TrackDataModel = { id, value, type, period, last_check, _ ->
    TrackDataModel(id, value, type, period, last_check?.let { SimpleDateFormatFactory.fromMillis(it) })
}

val leaksMapper: (
    id: Long,
    data: String,
    last_breach: String?,
    source: String?,
    from_line: String,
    from_line_type: TrackDataModel.TypeValue
) -> LeakModel = { id, data, last_breach, source, from_line, from_line_type ->
    LeakModel(id, data, last_breach, source, from_line, from_line_type)
}