package com.detsimov.leakchecker.data.network.services

import com.detsimov.leakchecker.data.network.dto.TrackDataDTO
import com.detsimov.leakchecker.data.network.services.i.ServiceFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

internal interface TrackDataService {

    companion object Factory : ServiceFactory<TrackDataService> {
        override fun create(retrofit: Retrofit): TrackDataService =
            retrofit.create(TrackDataService::class.java)
    }

    @POST("track")
    suspend fun add(@Body request: TrackDataDTO.Request.Create): TrackDataDTO.Response.Read

    @GET("track")
    suspend fun get(): List<TrackDataDTO.Response.Read>

    @HTTP(method = "DELETE", path = "track", hasBody = true)
    suspend fun delete(@Body request: TrackDataDTO.Request.Delete): Response<Unit>

    @HTTP(method = "DELETE", path = "track", hasBody = true)
    suspend fun deleteAll(@Body request: TrackDataDTO.Request.DeleteAll)
}