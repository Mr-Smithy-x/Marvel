package com.smith.ufc.data.base

/**
 * Created by Charlton on 10/31/17.
 */

interface Repository<T, S> {
    fun add(item: T)

    fun add(items: List<T>)

    fun update(item: T)

    fun remove(item: T)

    fun query(specification: S): List<T>
}