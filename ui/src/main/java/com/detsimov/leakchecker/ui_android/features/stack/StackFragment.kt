package com.detsimov.leakchecker.ui_android.features.stack

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.detsimov.core_ui.fragments.requireListener
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.features.stack.i.FragmentScreenContainer
import com.detsimov.leakchecker.ui_android.features.stack.i.StackInitFragmentFactory
import com.detsimov.leakchecker.ui_android.features.stack.models.StackScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.AppNavigator

class StackFragment : Fragment(R.layout.fragment_stack), FragmentScreenContainer {

    private val cicerone = Cicerone.create()
    override val router = cicerone.router

    private lateinit var stackInitFragmentFactory: StackInitFragmentFactory

    private val appNavigator by lazy {
        AppNavigator(
            requireActivity(),
            R.id.fragmentContainerView,
            fragmentManager = childFragmentManager
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        stackInitFragmentFactory = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        router.navigateTo(stackInitFragmentFactory.onInitFragment(getStackFromArgument()))
    }

    override fun onResume() {
        cicerone.getNavigatorHolder().setNavigator(appNavigator)
        super.onResume()
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    private fun getStackFromArgument() = requireArguments()[ARG_STACK] as StackScreen

    companion object {

        private const val ARG_STACK = "ARG_STACK"

        fun create(stackScreen: StackScreen) = StackFragment().apply {
            arguments = bundleOf(ARG_STACK to stackScreen)
        }
    }
}