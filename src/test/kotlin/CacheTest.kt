
import org.example.Cache
import org.junit.jupiter.api.Test

class CacheTest {
    @Test
    fun lfuStrategyMissElement(){
        val cache = Cache<String, Int>(Cache.Setting(5, Cache.Eviction.LFU))

        cache.add("1", 1)
        cache.add("2", 2)
        cache.add("3", 3)
        cache.add("4", 4)
        cache.add("5", 5)

        cache.retrieve("2")
        cache.retrieve("2")
        cache.retrieve("3")
        cache.retrieve("3")
        cache.retrieve("4")
        cache.retrieve("4")
        cache.retrieve("5")
        cache.retrieve("5")

        cache.add("6", 6)
        assert(cache.retrieve("1") == null)

    }

    @Test
    fun lruStrategyMissElement(){
        val cache = Cache<String, Int>(Cache.Setting(5, Cache.Eviction.LRU))

        cache.add("1", 1)
        Thread.sleep(1000)
        cache.add("2", 2)
        cache.add("3", 3)
        cache.add("4", 4)
        cache.add("5", 5)

        cache.retrieve("2")
        cache.retrieve("2")
        cache.retrieve("3")
        cache.retrieve("3")
        cache.retrieve("4")
        cache.retrieve("4")
        cache.retrieve("5")
        cache.retrieve("5")

        cache.add("6", 6)
        assert(cache.retrieve("1") == null)
    }
}