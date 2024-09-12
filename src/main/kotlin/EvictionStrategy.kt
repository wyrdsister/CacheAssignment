package org.example

interface EvictionStrategy<K, V> {

    val maxCapacity: Int

    fun add(key: K, value: V)

    fun retrieve(key: K) : V?

    fun clear()
}