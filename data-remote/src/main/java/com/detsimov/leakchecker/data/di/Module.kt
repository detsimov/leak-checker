package com.detsimov.leakchecker.data.di

import com.detsimov.leakchecker.data.datasources.TokenDataSource
import com.detsimov.leakchecker.data.network.providers.OkHttpProvider
import com.detsimov.leakchecker.data.network.providers.RetrofitProvider
import com.detsimov.leakchecker.data.network.services.LeakService
import com.detsimov.leakchecker.data.network.services.TokenService
import com.detsimov.leakchecker.data.network.services.TrackDataService
import com.detsimov.leakchecker.data.repositories.*
import com.detsimov.leakchecker.domain.repositories.*
import org.koin.dsl.module

val dataModule = module {

    /** Network */
    single { OkHttpProvider(get()).get() }
    single { RetrofitProvider(get()).get() }

    single { TokenService.create(get()) }
    single { TrackDataService.create(get()) }
    single { LeakService.create(get()) }

    /** Repositories */
    single<ITokenRepository> { TokenRepository(get(),get()) }
    single<IAppInfoRepository> { DeviceInfoRepository(get()) }
    single<ITrackDataRepository> { TrackDataRepository(get()) }
    single<IRuleRepository> { RuleRepository() }
    single<ILeakRepository> { LeakRepository(get()) }

    /** DataSources */
    single { TokenDataSource(get()) }
}