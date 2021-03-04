package com.detsimov.leakchecker.data_local.repositories

import android.provider.Settings
import com.detsimov.leakchecker.domain.repositories.IAppInfoRepository

class DeviceInfoRepository : IAppInfoRepository {

    override suspend fun id(): String = Settings.Secure.ANDROID_ID
}