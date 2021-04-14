package com.detsimov.leakchecker.domain.di

import com.detsimov.leakchecker.domain.interactors.AuthInteractor
import com.detsimov.leakchecker.domain.interactors.LeakInteractor
import com.detsimov.leakchecker.domain.interactors.SecureInteractor
import com.detsimov.leakchecker.domain.interactors.TrackDataInteractor
import com.detsimov.leakchecker.domain.interactors.i.IAuthInteractor
import com.detsimov.leakchecker.domain.interactors.i.ILeakInteractor
import com.detsimov.leakchecker.domain.interactors.i.ISecureInteractor
import com.detsimov.leakchecker.domain.interactors.i.ITrackDataInteractor
import org.koin.dsl.module

val domainModule = module {

    /** Interactors */
    single<ITrackDataInteractor> { TrackDataInteractor(get(),get()) }
    single<IAuthInteractor> { AuthInteractor(get(),get()) }
    single<ILeakInteractor> { LeakInteractor(get()) }
    single<ISecureInteractor> { SecureInteractor(get(),get(),get()) }
}