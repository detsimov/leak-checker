package com.detsimov.leakchecker.data.network.services.i

import retrofit2.Retrofit

internal interface ServiceFactory<S> {

    fun create(retrofit: Retrofit) : S
}