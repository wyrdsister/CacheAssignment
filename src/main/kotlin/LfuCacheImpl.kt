package org.example

class LfuCache<K, V>(override val maxCapacity: Int) : ICache<K, V> {

    private val keyToValue = HashMap<K, V>(maxCapacity)
    private val keyToFrequency = HashMap<K,Int>(maxCapacity)
    private val frequencyToKeys = HashMap<Int, LinkedHashSet<K>>()
    private var minFrequency = 0

    override fun add(key: K, value: V) : Boolean{
        if (maxCapacity < 1)
            return false

        minFrequency = 0

        if (keyToValue.containsKey(key)){
            keyToValue[key] = value
            retrieve(key)
            return true
        }

        if (keyToValue.size == maxCapacity)
            evict()

        keyToValue[key] = value
        addOrUpdateFrequency(key, minFrequency)

        return true
    }

    override fun retrieve(key: K) : V? {
        if (!keyToValue.containsKey(key))
            return null

        val oldKeyFrequency = keyToFrequency[key]!!
        frequencyToKeys[oldKeyFrequency]!!.remove(key)
        if (oldKeyFrequency == minFrequency && frequencyToKeys[oldKeyFrequency]!!.isEmpty()){
            frequencyToKeys.remove(oldKeyFrequency)
            minFrequency++
        }

        addOrUpdateFrequency(key, oldKeyFrequency + 1)

        return keyToValue[key]
    }

    override fun evict() {
        val evictionKey = frequencyToKeys[minFrequency]?.first()
        frequencyToKeys[minFrequency]?.remove(evictionKey)
        keyToFrequency.remove(evictionKey)
        keyToValue.remove(evictionKey)
    }

    private fun addOrUpdateFrequency(key : K, frequency: Int){
        keyToFrequency[key] = frequency
        frequencyToKeys.getOrPut(frequency){LinkedHashSet()}.add(key)
    }
}