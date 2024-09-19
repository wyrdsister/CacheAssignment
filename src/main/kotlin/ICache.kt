package org.example

interface ICache<K, V> {

    val maxCapacity: Int

    fun add(key: K, value: V) : Boolean

    fun retrieve(key: K) : V?

    fun evict()
}