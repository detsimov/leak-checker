package com.detsimov.leakchecker.domain.repositories

import com.detsimov.leakchecker.domain.exceptions.TokenException
import com.detsimov.leakchecker.domain.models.AccountModel

interface ITokenRepository {

    suspend fun createAndSave(accountModel: AccountModel)

    suspend fun get() : String?

    suspend fun require() : String

}

class TokenIsEmptyException : TokenException("Token is not in the cache")