package com.detsimov.leakchecker.data.repositories

import com.detsimov.leakchecker.data.datasources.TokenDataSource
import com.detsimov.leakchecker.data.network.dto.TokenDTO
import com.detsimov.leakchecker.data.network.services.TokenService
import com.detsimov.leakchecker.domain.models.AccountModel
import com.detsimov.leakchecker.domain.repositories.ITokenRepository
import com.detsimov.leakchecker.domain.repositories.TokenIsEmptyException

internal class TokenRepository(
    private val tokenDataSource: TokenDataSource,
    private val tokenService: TokenService
) : ITokenRepository {

    override suspend fun createAndSave(accountModel: AccountModel) {
        tokenService.create(TokenDTO.Request.Create(accountModel.userAndroidId)).apply {
            tokenDataSource.token = token
        }
    }

    override suspend fun get()  = tokenDataSource.token

    override suspend fun require(): String  = tokenDataSource.token ?: throw TokenIsEmptyException()

}