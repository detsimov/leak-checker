package com.detsimov.core_data

interface ResponseMapper<E, M> {

    fun E.transform() : M
}