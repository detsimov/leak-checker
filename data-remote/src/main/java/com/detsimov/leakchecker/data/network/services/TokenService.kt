package com.detsimov.leakchecker.data.network.services

import com.detsimov.leakchecker.data.network.dto.TokenDTO
import com.detsimov.leakchecker.data.network.services.i.ServiceFactory
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

internal interface TokenService {

    companion object Factory : ServiceFactory<TokenService> {
        override fun create(retrofit: Retrofit): TokenService =
            retrofit.create(TokenService::class.java)
    }

    @POST("user")
    suspend fun create(@Body request: TokenDTO.Request.Create): TokenDTO.Response.Create

}