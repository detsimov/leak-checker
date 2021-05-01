package com.detsimov.leakchecker.domain.interactors

import com.detsimov.leakchecker.domain.interactors.i.IAuthInteractor
import com.detsimov.leakchecker.domain.models.AccountModel
import com.detsimov.leakchecker.domain.repositories.IUserInfoRepository
import com.detsimov.leakchecker.domain.repositories.ITokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class AuthInteractor(
    private val tokenRepository: ITokenRepository,
    private val appInfoRepository: IUserInfoRepository
) : IAuthInteractor {

    override suspend fun authenticate() = withContext(Dispatchers.IO) {
        if (isAuthorized().not()) {
            tokenRepository.createAndSave(AccountModel(appInfoRepository.id(), false))
        }
    }

    override suspend fun isAuthorized(): Boolean = withContext(Dispatchers.IO) {
        tokenRepository.get() != null
    }
}