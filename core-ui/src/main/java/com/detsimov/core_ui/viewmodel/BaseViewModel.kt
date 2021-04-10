package com.detsimov.core_ui.viewmodel

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.detsimov.core_ui.livedata.asLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ViewModel", throwable.toString())
        handleError(throwable)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main + errorHandler

    private val _error = MutableLiveData<Throwable>()
    val error = _error.asLiveData()

    protected val _progress = MutableLiveData<Boolean>()
    val progress = _progress.asLiveData()

    @CallSuper
    protected open fun handleError(throwable: Throwable) {
        _progress.value = false
        _error.value = throwable
    }

    override fun onCleared() {
        coroutineContext.cancelChildren()
        super.onCleared()
    }

}