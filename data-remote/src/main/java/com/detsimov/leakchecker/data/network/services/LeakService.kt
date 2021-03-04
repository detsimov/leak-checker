package com.detsimov.leakchecker.data.network.services

import com.detsimov.leakchecker.data.network.dto.LeakDTO
import com.detsimov.leakchecker.data.network.services.i.ServiceFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP

internal interface LeakService {

    companion object Factory : ServiceFactory<LeakService> {
        override fun create(retrofit: Retrofit): LeakService = retrofit.create(LeakService::class.java)
    }

    @GET("leak")
    suspend fun get() : List<LeakDTO.Response.Read>


    @HTTP(method = "DELETE", path = "leak", hasBody = true)
    suspend fun delete(@Body request: LeakDTO.Request.Delete): Response<Unit>

    @HTTP(method = "DELETE", path = "leak", hasBody = true)
    suspend fun deleteAll(@Body request: LeakDTO.Request.DeleteAll) : Response<Unit>
}