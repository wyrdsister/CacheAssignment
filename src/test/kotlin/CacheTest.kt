
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull

open class CacheTest {
    fun testAddAndRetrieve(eviction: Eviction){
        val cache = Cache<Int, String>(Cache.Setting(3, eviction))

        cache.add(1, "A")
        cache.add(2, "B")
        cache.add(3, "C")

        assertEquals("A", cache.retrieve(1))
        assertEquals("B", cache.retrieve(2))
        assertEquals("C", cache.retrieve(3))
    }

    fun testEvictionWhenCapacityExceed(eviction: Eviction){
        val cache = Cache<Int, String>(Cache.Setting(4, eviction))

        cache.add(1, "A")
        cache.add(2, "B")
        cache.add(3, "C")
        cache.add(4, "D")

        cache.add(5, "E")

        assertNull(cache.retrieve(1))
        assertNotNull(cache.retrieve(5))
        assertNotNull(cache.retrieve(2))
        assertNotNull(cache.retrieve(3))
        assertNotNull(cache.retrieve(4))

    }

    fun testZeroCapacity(eviction: Eviction){
        val cache = Cache<Int, String>(Cache.Setting(0, eviction))

        assertFailsWith<IllegalStateException> { cache.add(1, "A") }
    }
}