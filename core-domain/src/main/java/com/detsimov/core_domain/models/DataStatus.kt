package com.detsimov.core_domain.models

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

sealed class DataStatus<out R : Any, out E : Throwable> {

    object Empty : DataStatus<Nothing, Throwable>()
    object Loading : DataStatus<Nothing, Throwable>()
    data class Success<R : Any>(val data: R) : DataStatus<R, Nothing>()
    data class Error<E : Throwable>(val exception: E) : DataStatus<Nothing, E>()


    inline fun empty(emptyCallback: () -> Unit): DataStatus<R, E> {
        if (this is Empty) emptyCallback.invoke()
        return this
    }

    inline fun loading(loadingCallback: () -> Unit): DataStatus<R, E> {
        if (this is Loading) loadingCallback.invoke()
        return this
    }

    inline fun error(errorCallback: (error: Throwable) -> Unit): DataStatus<R, E> {
        if (this is Error) errorCallback.invoke(exception)
        return this
    }

    inline fun successData(successCallback: (data: R) -> Unit): DataStatus<R, E> {
        if (this is Success) successCallback.invoke(data)
        return this
    }

}

class DataStatusSuccessDelegate<R : Any, E : Throwable>(private val callbackFrom: () -> DataStatus<R, E>) :
    ReadOnlyProperty<Any?, R?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): R? {
        val dataStatus = callbackFrom()
        return if (dataStatus is DataStatus.Success) dataStatus.data
        else null
    }

}

fun <R : Any, E : Throwable> dataStatusSuccessData(callbackFrom: () -> DataStatus<R, E>) =
    DataStatusSuccessDelegate(callbackFrom)


