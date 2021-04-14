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

    override val type: Int
        get() = R.id.item_leak_data

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
            if (model.lastBreach.isNullOrBlank().not()) {
                text = context.getString(R.string.leak_item_breach, model.lastBreach)
                isVisible = true
            } else {
                isVisible = false
            }
        }
    }

    private fun ItemLeakBinding.bindSource() {
        tvSource.apply {
            if(model.source.isNullOrBlank().not()) {
                text = context.getString(R.string.leak_item_source, model.source)
                isVisible = true
            } else {
                isVisible = false
            }
        }
    }

    private fun ItemLeakBinding.bindEmpty() {
        val isEmpty = model.run { source.isNullOrBlank() && lastBreach.isNullOrBlank() }
        tvEmpty.apply {
            visibility = if (isEmpty) View.VISIBLE else View.GONE
        }
    }
}