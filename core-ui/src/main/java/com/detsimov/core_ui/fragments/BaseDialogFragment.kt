package com.detsimov.core_ui.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.detsimov.core_ui.viewmodel.BaseViewModel

/**
 * @author Artyom Detsimov
 * Базовый класс для работы с диалог фрагментом
 */
abstract class BaseDialogFragment<VM : BaseViewModel>(private val layoutId: Int) :
    DialogFragment() {

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
        return View.inflate(context, layoutId, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        log("onViewCreated")
        setUpDialog()
        viewModel.progress.observe(viewLifecycleOwner) {
            if (it) onShowProgress()
            else onHideProgress()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            onShowError(it)
        }
    }

    private fun setUpDialog(){
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    protected open fun onShowError(error: Throwable) {}
    protected open fun onShowProgress() {}
    protected open fun onHideProgress() {}

    override fun onResume() {
        log("onResume")
        super.onResume()
    }

    override fun onStart() {
        log("onStart")
        super.onStart()
    }

    override fun onPause() {
        log("onPause")
        super.onPause()
    }

    override fun onStop() {
        log("onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }
}


