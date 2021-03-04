package com.detsimov.core_data

interface Provider<T> {
    fun get() : T
}