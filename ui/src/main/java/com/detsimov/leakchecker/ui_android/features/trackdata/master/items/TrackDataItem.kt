package com.detsimov.leakchecker.ui_android.features.trackdata.master.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.ItemTrackDataBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem

class TrackDataItem(private val trackData: TrackDataModel) :
    ModelAbstractBindingItem<TrackDataModel, ItemTrackDataBinding>(trackData) {

    override var identifier: Long = trackData.id

    override val type: Int
        get() = R.id.item_track_data

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemTrackDataBinding {
        return ItemTrackDataBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemTrackDataBinding, payloads: List<Any>) {
        binding.apply {
            bindData()
            bindImageOfType()
            bindLastCheck()
        }
    }

    private fun ItemTrackDataBinding.bindData() {
        tvData.apply {
            text = trackData.value
        }
    }

    private fun ItemTrackDataBinding.bindLastCheck() {
        tvLastCheckDate.apply {
            text = context.getString(
                R.string.track_data_item_last_check,
                trackData.lastCheck
                    ?: context.getString(R.string.track_data_item_last_check_default)
            )
        }
    }

    private fun ItemTrackDataBinding.bindImageOfType() {
        imgType.setImageResource(
            if (trackData.type == TrackDataModel.TypeValue.PHONE) R.drawable.ic_baseline_local_phone_24
            else R.drawable.ic_baseline_email_24
        )
    }
}