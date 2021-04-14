package com.detsimov.leakchecker.data_local.repositories

import com.detsimov.leakchecker.data_local.datasources.TokenDataSource
import com.detsimov.leakchecker.data_local.network.dto.TokenDTO
import com.detsimov.leakchecker.data_local.network.services.TokenService
import com.detsimov.leakchecker.domain.models.AccountModel
import com.detsimov.leakchecker.domain.repositories.ITokenRepository
import com.detsimov.leakchecker.domain.repositories.TokenIsEmptyException

internal class TokenRepository(
    private val tokenService: TokenService,
    private val tokenDataSource: TokenDataSource
) : ITokenRepository {

    override suspend fun createAndSave(accountModel: AccountModel) {
        tokenDataSource.token =
            tokenService.create(TokenDTO.Request.Create(accountModel.userAndroidId)).token
    }

    override suspend fun get(): String? = tokenDataSource.token

    override suspend fun require(): String = tokenDataSource.token ?: throw TokenIsEmptyException()
}