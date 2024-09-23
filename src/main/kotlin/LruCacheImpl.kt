package org.example

class LruCache<K, V>(override val maxCapacity: Int) : ICache<K, V> {

    private val keyToValue = object : LinkedHashMap<K, V>(maxCapacity, 1f, true){
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            return this.size > maxCapacity
        }
    }

    override fun add(key: K, value: V) {
        keyToValue[key] = value
    }

    override fun retrieve(key: K): V? {
        return keyToValue[key]
    }

    override fun evict() {}

}
