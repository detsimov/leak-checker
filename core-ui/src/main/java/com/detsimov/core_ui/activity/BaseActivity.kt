package com.detsimov.core_ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.detsimov.core_ui.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progress.observe(this) {
            if (it) onShowProgress()
            else onHideProgress()
        }
        viewModel.error.observe(this) {
            onShowError(it)
        }
    }

    /**
     * Отображение сообщения или еще чего либо при ошибке
     * @param error ошибка из [viewModel]
     */
    open fun onShowError(error: Throwable) {}

    /**
     * Показать/Спрятать прогресс из [viewModel]
     */
    open fun onShowProgress() {}
    open fun onHideProgress() {}
}