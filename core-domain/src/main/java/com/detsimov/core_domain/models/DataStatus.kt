package com.detsimov.core_domain.models

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


