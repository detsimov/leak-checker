package com.detsimov.core_ui.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.update(updateCallback: MutableList<T>.() -> Unit) =
    submitList(currentList.toMutableList().apply(updateCallback))


fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.submitItem(item: T) = update {
    add(0, item)
}

fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.submitItems(items: List<T>) = update {
    addAll(0, items)
}

fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.deleteItem(item: T) = update {
    remove(item)
}

fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.deleteItems(items: List<T>) = update {
    removeAll(items)
}

fun <T, VH : RecyclerView.ViewHolder> ListAdapter<T, VH>.deleteAllItems() = update {
    clear()
}