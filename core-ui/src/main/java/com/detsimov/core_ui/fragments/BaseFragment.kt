package com.detsimov.core_ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.detsimov.core_ui.viewmodel.BaseViewModel

/**
 * Базовый класс для работы с фрагментом
 */
abstract class BaseFragment<VM : BaseViewModel>(
    layoutId: Int
) : Fragment(layoutId) {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        log("onViewCreated")
        viewModel.progress.observe(viewLifecycleOwner, {
            if (it) onShowProgress()
            else onHideProgress()
        })
        viewModel.error.observe(viewLifecycleOwner, {
            onShowError(it)
        })
    }


    /**
     * Отображение сообщения или еще чего либо при ошибке
     * @param error ошибка из [viewModel]
     */
    protected open fun onShowError(error: Throwable) {
        toast("Handle unknown error $error")
    }


    /**
     * Показать/Спрятать прогресс из [viewModel]
     */
    protected open fun onShowProgress() {}
    protected open fun onHideProgress() {}


    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

}


