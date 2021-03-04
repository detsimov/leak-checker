package com.detsimov.leakchecker.data.utils

import java.text.SimpleDateFormat
import java.util.*

object SimpleDateFormatFactory {

    fun create() = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.getDefault())
}