package org.example

class Cache<K, V>(setting: Setting) {

    enum class Eviction {
        LRU,
        LFU
    }

    data class Setting(val maxCapacity: Int, val evictionStrategy: Eviction)

    val evictionType: EvictionStrategy<K, V>

    init {
        when(setting.evictionStrategy){
            Eviction.LFU -> evictionType = LfuStrategy<K, V>(setting.maxCapacity)
            Eviction.LRU -> evictionType = LruStrategy<K, V>(setting.maxCapacity)
        }

    }

    fun add(key: K, element: V) {
        evictionType.add(key, element)
    }

    fun retrieve(key: K): V? {
        return evictionType.retrieve(key)
    }

    private fun clear() {
        evictionType.clear()
    }

}