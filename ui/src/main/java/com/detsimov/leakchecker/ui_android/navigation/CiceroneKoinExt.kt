package com.detsimov.leakchecker.ui_android.navigation

import android.content.ComponentCallbacks
import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.get
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope

inline fun <reified T : BaseRouter> ComponentCallbacks.cicerone(ciceroneQualifier: CiceroneQualifier) =
    get<Cicerone<T>>(qualifier(ciceroneQualifier.tag))

inline fun <reified T : BaseRouter> ComponentCallbacks.router(ciceroneQualifier: CiceroneQualifier) =
    get<T>(qualifier(ciceroneQualifier.tag))


inline fun <reified T : BaseRouter> Module.cicerone(router: T, ciceroneQualifier: CiceroneQualifier) {
    single(qualifier = qualifier(ciceroneQualifier.tag)) { Cicerone.create(router) }
    single(qualifier(ciceroneQualifier.tag)) { get<Cicerone<T>>(qualifier(ciceroneQualifier.tag)).router }
}

inline fun <reified T : BaseRouter> Scope.router(ciceroneQualifier: CiceroneQualifier) =
    get<T>(qualifier(ciceroneQualifier.tag))


inline fun <reified T : BaseRouter> Scope.cicerone(ciceroneQualifier: CiceroneQualifier) =
    get<Cicerone<T>>(qualifier(ciceroneQualifier.tag))

open class CiceroneQualifier {
    open val tag = this::class.java.name
}


