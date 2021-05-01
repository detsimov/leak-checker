package com.detsimov.leakchecker.data_local.di

import com.detsimov.leakchecker.data_local.database.DatabaseProvider
import com.detsimov.leakchecker.data_local.datasources.SecureDataSource
import com.detsimov.leakchecker.data_local.datasources.TokenDataSource
import com.detsimov.leakchecker.data_local.datasources.UserInfoDataSource
import com.detsimov.leakchecker.data_local.network.providers.OkHttpProvider
import com.detsimov.leakchecker.data_local.network.providers.RetrofitProvider
import com.detsimov.leakchecker.data_local.network.services.SecureService
import com.detsimov.leakchecker.data_local.network.services.TokenService
import com.detsimov.leakchecker.data_local.repositories.*
import com.detsimov.leakchecker.data_local.security.Secure
import com.detsimov.leakchecker.domain.repositories.*
import com.detsimov.leakchecker.domain.service.ISecure
import org.koin.dsl.module

val dataModule = module {

    /** Network */
    single { OkHttpProvider(get(),get()).get() }
    single { RetrofitProvider(get()).get() }

    single { TokenService.create(get()) }
    single { SecureService.create(get()) }

    /** Repositories */
    single<ITokenRepository> { TokenRepository(get(), get()) }
    single<IUserInfoRepository> { UserInfoRepository(get()) }
    single<ITrackDataRepository> { TrackDataRepository(get()) }
    single<IRuleRepository> { RuleRepository() }
    single<ILeakRepository> { LeakRepository(get()) }

    /** Security */
    single<ISecure> { Secure(get(), get()) }

    /** DataSources */
    single { TokenDataSource(get()) }
    single { UserInfoDataSource(get()) }
    single { SecureDataSource(get()) }

    /** Providers */
    single { DatabaseProvider(get()).get() }
}