package com.detsimov.leakchecker.data_local.datasources

import android.content.Context
import com.detsimov.core_data.DataSource
import com.detsimov.core_data.boolean

internal class UserInfoDataSource(applicationContext: Context) : DataSource {

    private val sharedPreferences = applicationContext.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    var isAcceptedConsent = sharedPreferences.boolean(PREF_KEY_IS_ACCEPTED_CONSENT, false)

    companion object {

        private const val PREF_FILE_NAME = "USER_INFO"
        private const val PREF_KEY_IS_ACCEPTED_CONSENT = "IS_ACCEPTED_CONSENT"
    }

}