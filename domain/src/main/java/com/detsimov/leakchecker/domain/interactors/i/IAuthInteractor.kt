package com.detsimov.leakchecker.domain.interactors.i

import com.detsimov.core_domain.BaseInteractor

interface IAuthInteractor : BaseInteractor {

    suspend fun authenticate()

    suspend fun isAuthorized() : Boolean
}