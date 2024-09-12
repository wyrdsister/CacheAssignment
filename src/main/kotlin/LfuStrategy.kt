package org.example

class LfuStrategy<K, V>(override val maxCapacity: Int) : EvictionStrategy<K, V> {

    data class CacheElement<K, V>(
        val key: K,
        val obj: V,
        var used: Int = 0
    )

    private var storage: Array<CacheElement<K, V>?> = Array(maxCapacity) { null }

    override fun add(key: K, value: V) {
        if (storage.none() { it?.obj == value }) {
            var index = storage.indexOf(null)
            if (index == -1) {
                clear()
                index = storage.indexOf(null)
            }

            storage[index] = CacheElement(key, value)
        }
    }

    override fun retrieve(key: K) : V? {
        val element = storage.find { it?.key == key }
        if (element != null) {
            element.used++
            return element.obj
        } else {
            return null
        }
    }

    override fun clear() {
        while(storage.none() { it == null }){
            var clearLimit = 2
            for (index in storage.indices) {
                val element = storage[index]
                if (element != null && element.used < clearLimit) {
                    storage[index] = null
                }
            }

            clearLimit += 2
        }
    }
}