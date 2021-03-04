package com.detsimov.leakchecker.data.repositories

import android.content.Context
import android.provider.Settings
import com.detsimov.leakchecker.domain.repositories.IAppInfoRepository

class DeviceInfoRepository(private val context: Context) : IAppInfoRepository {

    override suspend fun id(): String = Settings.Secure.ANDROID_ID
}