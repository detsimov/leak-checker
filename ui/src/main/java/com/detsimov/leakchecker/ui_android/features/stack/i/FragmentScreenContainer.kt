package com.detsimov.leakchecker.ui_android.features.stack.i

import androidx.fragment.app.Fragment
import com.detsimov.core_ui.fragments.requireListener
import com.github.terrakok.cicerone.Router

interface FragmentScreenContainer {

    val router: Router
}

fun Fragment.getSelfRouter() = requireListener<FragmentScreenContainer>().router