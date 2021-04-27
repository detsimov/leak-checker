package com.detsimov.leakchecker.data_local.network.providers

import com.detsimov.core_data.Provider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal class RetrofitProvider(private val okHttpClient: OkHttpClient) : Provider<Retrofit> {

    companion object {
        private const val SERVER_URL = "https://etaapatia.ru/v1/leakchecker/"
        private const val contentType = "application/json"
    }

    override fun get(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SERVER_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.get(contentType)))
            .build()

}