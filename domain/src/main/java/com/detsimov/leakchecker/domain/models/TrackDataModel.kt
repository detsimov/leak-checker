package com.detsimov.leakchecker.domain.models

import com.detsimov.leakchecker.domain.utils.SimpleDateFormatFactory
import java.util.concurrent.TimeUnit

data class TrackDataModel(
    val id: Long = 0,
    val value: String,
    val type: TypeValue,
    val period: Period,
    val lastCheck: String?
) {

    fun isCanScan() = SimpleDateFormatFactory.toMillis(lastCheck) +
            TimeUnit.DAYS.toMillis(period.days.toLong()) < System.currentTimeMillis()

    enum class TypeValue {
        EMAIL,
        PHONE
    }

    enum class Period(val days: Int) {
        DEFAULT(1)
    }

    companion object {

        const val MAX_COUNT = 3
    }
}