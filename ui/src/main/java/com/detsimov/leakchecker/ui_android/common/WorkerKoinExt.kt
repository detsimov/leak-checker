package com.detsimov.leakchecker.ui_android.common

import androidx.work.ListenableWorker
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

fun ListenableWorker.getKoin() = GlobalContext.get()

inline fun <reified T : Any> ListenableWorker.inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null
) = lazy(mode) { get<T>(qualifier, parameters) }


inline fun <reified T : Any> ListenableWorker.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = getKoin().get(qualifier, parameters)
