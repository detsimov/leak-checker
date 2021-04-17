package com.detsimov.leakchecker.domain.repositories

interface IUserInfoRepository{

    var isAcceptedConsent: Boolean

    suspend fun id() : String
}