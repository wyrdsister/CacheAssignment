import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.time.measureTime

class LruCacheTest : CacheTest() {

    @Test
    fun testAddAndRetrieve(){
        testAddAndRetrieve(Eviction.LRU)
    }

    @Test
    fun testEvictionWhenCapacityExceed(){
        testEvictionWhenCapacityExceed(Eviction.LRU)
    }

    @Test
    fun testZeroCapacity(){
        testZeroCapacity(Eviction.LRU)
    }

    @Test
    fun testRetrieveAndUpdateRecentness(){
        val cache = Cache<Int, String>(Cache.Setting(5, Eviction.LRU))

        cache.add(1, "A")
        cache.add(2, "B")
        cache.add(3, "C")
        cache.add(4, "D")
        cache.add(5, "E")

        cache.retrieve(1)
        cache.retrieve(2)
        cache.retrieve(3)
        cache.retrieve(4)
        cache.retrieve(5)
        cache.retrieve(2)
        cache.retrieve(3)
        cache.retrieve(4)
        cache.retrieve(5)

        cache.add(6, "F")
        cache.add(7, "G")

        assertNull(cache.retrieve(1))
        assertNull(cache.retrieve(2))
        assertNotNull(cache.retrieve(3))
        assertNotNull(cache.retrieve(4))
        assertNotNull(cache.retrieve(5))
        assertNotNull(cache.retrieve(6))
        assertNotNull(cache.retrieve(7))
    }
}