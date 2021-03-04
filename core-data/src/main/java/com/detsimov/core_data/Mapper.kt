package com.detsimov.core_data

abstract class Mapper<E, M> {

    abstract fun transformToEntity(model: M): E
    abstract fun transformToModel(entity: E): M

    fun transformToEntity(model: List<M>): List<E> = model.map { transformToEntity(it) }
    fun transformToModel(entity: List<E>): List<M> = entity.map { transformToModel(it) }
}

//inline fun <T : Any, O> T.mapTo(transform: (T) -> O) = transform.invoke(this)