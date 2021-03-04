package com.detsimov.leakchecker.data_local.network.services

import com.detsimov.leakchecker.data_local.network.dto.SecureDTO
import com.detsimov.leakchecker.data_local.network.services.i.ServiceFactory
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

internal interface SecureService {

    companion object Factory : ServiceFactory<SecureService> {
        override fun create(retrofit: Retrofit): SecureService = retrofit.create()
    }

    @POST("secure")
    suspend fun scan(@Body request: SecureDTO.Request.Scan): SecureDTO.Response.Scan

}