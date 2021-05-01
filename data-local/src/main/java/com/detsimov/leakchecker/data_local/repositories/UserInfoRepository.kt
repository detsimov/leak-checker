package com.detsimov.leakchecker.data_local.repositories

import com.detsimov.leakchecker.data_local.datasources.UserInfoDataSource
import com.detsimov.leakchecker.domain.repositories.IUserInfoRepository

internal class UserInfoRepository(dataSource: UserInfoDataSource) : IUserInfoRepository {

    override var isAcceptedConsent: Boolean by dataSource.isAcceptedConsent

    override suspend fun id(): String = "empty"
}