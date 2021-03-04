package com.detsimov.leakchecker.domain.interactors

import com.detsimov.leakchecker.domain.interactors.i.IAuthInteractor
import com.detsimov.leakchecker.domain.models.AccountModel
import com.detsimov.leakchecker.domain.repositories.IAppInfoRepository
import com.detsimov.leakchecker.domain.repositories.ITokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class AuthInteractor(
    private val tokenRepository: ITokenRepository,
    private val appInfoRepository: IAppInfoRepository
) : IAuthInteractor {


    override suspend fun authenticate(): Unit = withContext(Dispatchers.IO) {
        delay(1500)
        if (isAuthorized().not()) tokenRepository.createAndSave(AccountModel(appInfoRepository.id(), false))
    }

    override suspend fun isAuthorized(): Boolean = withContext(Dispatchers.IO) {
        tokenRepository.get() != null
    }
}