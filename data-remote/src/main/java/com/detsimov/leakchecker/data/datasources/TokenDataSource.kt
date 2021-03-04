package com.detsimov.leakchecker.data.datasources

import android.content.Context
import com.detsimov.core_data.DataSource
import com.detsimov.core_data.prefStringNullable

internal class TokenDataSource(context: Context) : DataSource {

    companion object {
        private const val PREF_NAME = "TokenDataSource"
        private const val PREF_KEY_TOKEN = "Token"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var token by sharedPreferences.prefStringNullable(PREF_KEY_TOKEN)

}
