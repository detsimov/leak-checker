package com.detsimov.leakchecker.data_local.datasources

import android.content.Context
import com.detsimov.core_data.DataSource
import com.detsimov.core_data.int
import com.detsimov.core_data.string

internal class SecureDataSource(context: Context) : DataSource {

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var scanCount = sharedPreferences.int(PREF_KEY_SCAN_COUNT, 0)

    companion object {
        private const val PREF_NAME = "SecureDataSource"
        private const val PREF_KEY_SCAN_COUNT = "ScanCount"
    }
}