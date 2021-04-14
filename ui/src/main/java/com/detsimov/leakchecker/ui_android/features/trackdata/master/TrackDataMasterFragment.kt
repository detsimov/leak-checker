package com.detsimov.leakchecker.ui_android.features.trackdata.master

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.detsimov.core_ui.fragments.BaseFragment
import com.detsimov.core_ui.fragments.showOptimize
import com.detsimov.core_ui.fragments.toast
import com.detsimov.core_ui.view.startProgress
import com.detsimov.core_ui.view.stopProgress
import com.detsimov.leakchecker.domain.interactors.i.TrackDataOverflowException
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.common.DiffCallbackModelImpl
import com.detsimov.leakchecker.ui_android.databinding.FragmentMasterTrackdataBinding
import com.detsimov.leakchecker.ui_android.features.trackdata.creator.models.TrackDataCreateUi
import com.detsimov.leakchecker.ui_android.features.trackdata.creator.view.TrackDataCreateDialog
import com.detsimov.leakchecker.ui_android.features.trackdata.master.items.TrackDataItem
import com.detsimov.leakchecker.ui_android.firebase.Analytics
import com.detsimov.leakchecker.ui_android.firebase.EVENT
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackDataMasterFragment :
    BaseFragment<TrackDataMasterViewModel>(R.layout.fragment_master_trackdata),
    TrackDataCreateDialog.Owner {

    override val viewModel: TrackDataMasterViewModel by viewModel()

    private val viewBinding by viewBinding(FragmentMasterTrackdataBinding::bind)

    private val trackDataItemAdapter = ItemAdapter<TrackDataItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpButtons()
        setUpRecyclerView()
        setUpToolbar()
        setUpSwipeRefreshLayout()
        with(viewModel) {
            progressSaveTrackData.observe(viewLifecycleOwner) {
                setProgressSaveTrackData(it)
            }
            showTrackDataCreateDialog.observe(viewLifecycleOwner) {
                showTrackDataCreateDialog()
            }
            showTrackDataDeleteAllDialog.observe(viewLifecycleOwner) {
                showTrackDataClearAllDialog()
            }
            showTrackDataDeleteDialog.observe(viewLifecycleOwner) {
                showTrackDataDeleteDialog(it)
            }
            trackData.observe(viewLifecycleOwner) {
                FastAdapterDiffUtil.set(trackDataItemAdapter, it, DiffCallbackModelImpl())
            }
            clearTrackData.observe(viewLifecycleOwner) {
                trackDataItemAdapter.clear()
            }
        }
    }

    private fun setProgressSaveTrackData(isProgress: Boolean) {
        viewBinding.btnAddTrack.apply {
            if (isProgress) startProgress(Color.WHITE)
            else stopProgress(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_baseline_add_24,
                    null
                )
            )
        }
    }

    override fun onShowError(error: Throwable) {
        when (error) {
            is TrackDataOverflowException -> {
                Analytics.sendEvent(EVENT.USER_ADD_TRACK_DATA_CATCH_OVERFLOW)
                toast(getString(R.string.track_data_master_error_overflow))
            }
            else -> super.onShowError(error)
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

    private fun setUpButtons() {
        viewBinding.apply {
            btnAddTrack.setOnClickListener {
                viewModel.onShowTrackDataCreateDialog()
            }
        }
    }

    private fun setUpSwipeRefreshLayout() {
        viewBinding.swipeRefreshLayout.apply {
            isEnabled = false
        }
    }

    private fun showTrackDataPopupMenu(anchorView: View, item: TrackDataItem) {
        PopupMenu(context, anchorView, Gravity.END).apply {
            inflate(R.menu.menu_track_data_item)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.btnDelete -> {
                        viewModel.onShowTrackDataDeleteDialog(item.model)
                        true
                    }
                    else -> false
                }
            }
        }.show()
    }

    private fun setUpToolbar() {
        viewBinding.toolbar.apply {
            inflateMenu(R.menu.menu_home)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.btnDeleteAll -> {
                        viewModel.onShowTrackDataDeleteAllDialog()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        viewBinding.listTrackData.adapter = FastAdapter.with(trackDataItemAdapter).apply {
            onLongClickListener = { view, _, item, _ ->
                showTrackDataPopupMenu(view, item)
                true
            }
        }
    }

    private fun showTrackDataClearAllDialog() {
        showTrackDataRationaleDialog(
            getString(R.string.track_data_master_message_dialog_clear_all)
        ) {
            viewModel.onDeleteAllTrackData()
        }
    }

    private fun showTrackDataDeleteDialog(trackData: TrackDataModel) {
        showTrackDataRationaleDialog(
            getString(
                R.string.track_data_master_message_dialog_delete,
                trackData.value,
                trackData.type
            )
        ) {
            viewModel.onDeleteTrackData(trackData)
        }
    }

    private fun showTrackDataRationaleDialog(
        message: String,
        actionOk: () -> Unit
    ) = AlertDialog.Builder(requireContext())
        .setTitle(getString(R.string.track_data_master_title_dialog_delete))
        .setMessage(message)
        .setPositiveButton(getString(R.string.track_data_master_btn_ok_dialog_delete)) { _, _ ->
            actionOk()
        }
        .setNegativeButton(getString(R.string.track_data_master_btn_cancel_dialog_delete), null)
        .show()

    private fun showTrackDataCreateDialog() {
        TrackDataCreateDialog.create()
            .showOptimize(childFragmentManager, TrackDataCreateDialog.TAG)
    }

    override fun onTrackDataCreated(trackDataCreateUi: TrackDataCreateUi) {
        viewModel.onSaveTrackData(trackDataCreateUi.value, trackDataCreateUi.type)
    }

    companion object {

        fun create() = TrackDataMasterFragment()
    }
}