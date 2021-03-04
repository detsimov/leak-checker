package com.detsimov.leakchecker.ui_android.common

import com.mikepenz.fastadapter.IModelItem
import com.mikepenz.fastadapter.diff.DiffCallback

open class BaseDiffCallbackModelImpl<Item : IModelItem<*, *>> : DiffCallback<Item> {

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.identifier == newItem.identifier


    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.model == newItem.model


    override fun getChangePayload(
        oldItem: Item,
        oldItemPosition: Int,
        newItem: Item,
        newItemPosition: Int
    ): Any? = null

}



