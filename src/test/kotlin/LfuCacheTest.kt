import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.time.measureTime

class LfuCacheTest : CacheTest() {
    @Test
    fun testAddAndRetrieve(){
        testAddAndRetrieve(Eviction.LFU)
    }

    @Test
    fun testEvictionWhenCapacityExceed(){
        testEvictionWhenCapacityExceed(Eviction.LFU)
    }

    @Test
    fun testZeroCapacity(){
        testZeroCapacity(Eviction.LFU)
    }

    @Test
    fun testRetrieveAndUpdateFrequency(){
        val cache = Cache<Int, String>(Cache.Setting(4, Eviction.LFU))

        cache.add(1, "A")
        cache.add(2, "B")
        cache.add(3, "C")
        cache.add(4, "D")

        cache.retrieve(2)
        cache.retrieve(2)
        cache.retrieve(3)
        cache.retrieve(3)
        cache.retrieve(4)
        cache.retrieve(3)

        cache.add(5, "E")

        assertNull(cache.retrieve(1))
        assertEquals("E", cache.retrieve(5))
        assertEquals("B", cache.retrieve(2))
        assertEquals("C", cache.retrieve(3))
        assertEquals("D", cache.retrieve(4))

    }

    @Test
    fun testAddConstantTime(){
        val cache = Cache<Int, String>(Cache.Setting(4, Eviction.LFU))

        val timeAddA = measureTime { cache.add(1, "A") }
        val timeAddB = measureTime { cache.add(2, "B") }
        val timeAddC = measureTime { cache.add(3, "C") }
        val timeAddD = measureTime { cache.add(4, "D") }
        val timeAddE = measureTime { cache.add(5, "E") }

        println(timeAddA)
        println(timeAddB)
        println(timeAddC)
        println(timeAddD)
        println(timeAddE)
    }
}