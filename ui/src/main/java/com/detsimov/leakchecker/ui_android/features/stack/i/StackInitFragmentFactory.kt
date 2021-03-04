package com.detsimov.leakchecker.ui_android.features.stack.i

import com.detsimov.leakchecker.ui_android.features.stack.models.StackScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface StackInitFragmentFactory {

    fun onInitFragment(stackScreen: StackScreen) : FragmentScreen
}