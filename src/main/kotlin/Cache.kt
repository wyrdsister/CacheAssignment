package org.example

import kotlin.random.Random
import kotlin.random.nextInt

class Cache<K, V>(setting: Setting) {
    enum class Eviction {
        LRU,
        LFU
    }

    data class Setting(val maxCapacity: Int, val evictionStrategy: Eviction)

    private val cacheImpl: ICache<K, V>

    init {
        cacheImpl = when(setting.evictionStrategy){
            Eviction.LFU -> LfuCache<K, V>(setting.maxCapacity)
            Eviction.LRU -> LfuCache<K, V>(setting.maxCapacity)
        }

    }

    fun add(key: K, value: V) = cacheImpl.add(key, value)

    fun retrieve(key: K): V? = cacheImpl.retrieve(key)


}