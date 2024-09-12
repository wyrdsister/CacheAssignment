package org.example

import java.time.Instant
import java.util.Date
import java.util.Stack
import java.util.Timer

class LruStrategy<K, V>(override val maxCapacity: Int) : EvictionStrategy<K, V> {

    data class CacheElement<K, V>(
        val key: K,
        val obj: V,
        var retrieveTime: Instant
    )

    private var storage: Array<CacheElement<K, V>?> = Array(maxCapacity) { null }
    override fun add(key: K, value: V) {
        if (storage.find { it?.key == key } != null) return

        var index = storage.indexOf(null)
        if (index == -1) {
            clear()
            index = storage.indexOf(null)
        }
        storage[index] = CacheElement(key, value, Instant.now())
    }

    override fun retrieve(key: K): V? {
        val element = storage.find { it?.key == key }
        if (element != null) {
            element.retrieveTime = Instant.now()
            return element.obj
        } else {
            return null
        }
    }

    override fun clear() {
        storage.sortByDescending { it?.retrieveTime?.nano }
        for (index in storage.size - (1 + (maxCapacity * 0.2).toInt())..storage.size - 1) {
            storage[index] = null
        }
    }
}