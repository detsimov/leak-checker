package com.detsimov.leakchecker.domain.repositories

interface IAppInfoRepository{

    suspend fun id() : String
}