package com.detsimov.leakchecker.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object SimpleDateFormatFactory {

    private val formatter by lazy {
        SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.getDefault())
    }

    fun fromMillis(millis: Long): String = formatter.format(Date(millis))

    fun toMillis(string: String?) : Long = if (string != null) formatter.parse(string)?.time ?: 0 else 0
}