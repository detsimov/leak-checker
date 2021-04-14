package com.detsimov.leakchecker.data_local.network.providers

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.detsimov.core_data.BuildConfig
import com.detsimov.core_data.Provider
import com.detsimov.leakchecker.data_local.datasources.TokenDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

internal class OkHttpProvider(private val context: Context, tokenDataSource: TokenDataSource) :
    Provider<OkHttpClient> {

    private val tokenInterceptor = TokenInterceptor(tokenDataSource)

    private val versionInterceptor = VersionInterceptor()

    override fun get(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .addInterceptor(tokenInterceptor)
            .addInterceptor(versionInterceptor)
            .build()
}

internal class TokenInterceptor(private val tokenDataSource: TokenDataSource) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return tokenDataSource.token?.let {
            chain.proceed(
                chain.request().resumeRequestWithAccessToken(it)
            )
        } ?: chain.proceed(chain.request())
    }

    private fun Request.resumeRequestWithAccessToken(accessToken: String) = newBuilder()
        .header("Access-Id", accessToken)
        .build()
}

internal class VersionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request().resumeRequestWithVersion(BuildConfig.VERSION_CODE)
    )

    private fun Request.resumeRequestWithVersion(version: Int) = newBuilder()
        .header("Version-Code", version.toString())
        .build()
}