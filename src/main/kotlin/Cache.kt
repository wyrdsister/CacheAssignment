import org.example.ICache
import org.example.LfuCache
import org.example.LruCache


class Cache<K, V>(setting: Setting) {

    data class Setting(val maxCapacity: Int, val evictionStrategy: Eviction)

    private val cacheImpl: ICache<K, V>

    init {
        cacheImpl = when (setting.evictionStrategy) {
            Eviction.LFU -> LfuCache<K, V>(setting.maxCapacity)
            Eviction.LRU -> LruCache<K, V>(setting.maxCapacity)
        }

    }

    fun add(key: K, value: V) {
        if (cacheImpl.maxCapacity < 1)
            throw IllegalStateException("Cannot add value to a cache with zero or negative capacity")

        cacheImpl.add(key, value)
    }

    fun retrieve(key: K): V? = cacheImpl.retrieve(key)


}