package com.detsimov.leakchecker.data_local.network.services.i

import retrofit2.Retrofit

internal interface ServiceFactory<S> {

    fun create(retrofit: Retrofit) : S
}