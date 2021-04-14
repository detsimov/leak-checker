package com.detsimov.leakchecker.ui_android.features.leak.master

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.common.DiffCallbackModelImpl
import com.detsimov.leakchecker.ui_android.databinding.FragmentMasterLeakBinding
import com.detsimov.leakchecker.ui_android.features.leak.item.LeakItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeakMasterFragment : BaseFragment<LeakMasterViewModel>(R.layout.fragment_master_leak) {

    override val viewModel by viewModel<LeakMasterViewModel>()

    private val viewBinding by viewBinding(FragmentMasterLeakBinding::bind)

    private val leakItemAdapter = ItemAdapter<LeakItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSwipeRefreshLayout()
        with(viewModel){
            leaks.observe(viewLifecycleOwner) {
                FastAdapterDiffUtil.set(leakItemAdapter, it, DiffCallbackModelImpl())
            }
        }
    }

    override fun onShowProgress() {
        viewBinding.swipeRefreshLayout.apply {
            isRefreshing = true
        }
    }

    override fun onHideProgress() {
        viewBinding.swipeRefreshLayout.apply {
            isRefreshing = false
        }
    }

    private fun setUpRecyclerView(){
        viewBinding.listLeaks.apply {
            adapter = FastAdapter.with(leakItemAdapter)
        }
    }

    private fun setUpSwipeRefreshLayout() {
        viewBinding.swipeRefreshLayout.apply {
            isEnabled = false
        }
    }

    companion object {

        fun create() = LeakMasterFragment()
    }
}