package com.detsimov.core_data

interface RequestFactory<E, T> {

    fun from(model: E): T
}