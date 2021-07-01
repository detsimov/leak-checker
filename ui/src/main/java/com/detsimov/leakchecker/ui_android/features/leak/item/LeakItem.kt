package com.detsimov.leakchecker.ui_android.features.leak.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.ui_android.R
import com.detsimov.leakchecker.ui_android.databinding.ItemLeakBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem

class LeakItem(model: LeakModel) : ModelAbstractBindingItem<LeakModel, ItemLeakBinding>(model) {

    override var identifier: Long = model.id

    override val type: Int = R.id.item_leak_data

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemLeakBinding =
        ItemLeakBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemLeakBinding, payloads: List<Any>) {
        binding.apply {
            bindData()
            bindDateOfLeak()
            bindSource()
            bindEmpty()
        }
    }

    private fun ItemLeakBinding.bindData() {
        tvData.text = model.data
    }

    private fun ItemLeakBinding.bindDateOfLeak() {
        tvDate.apply {
            val date = if (model.lastBreach.isNullOrBlank().not()) {
                context.getString(R.string.leak_item_breach, model.lastBreach)
            } else {
                ""
            }
            text = date
            isVisible = date.isNotEmpty()
        }
    }

    private fun ItemLeakBinding.bindSource() {
        tvSource.apply {
            val source = if (model.source.isNullOrBlank().not()) {
                context.getString(R.string.leak_item_source, model.source)
            } else {
                ""
            }
            text = source
            isVisible = source.isNotEmpty()
        }
    }

    private fun ItemLeakBinding.bindEmpty() {
        tvEmpty.isVisible = model.run { source.isNullOrBlank() && lastBreach.isNullOrBlank() }
    }
}