
package benchmarks

import Cache
import Eviction.LFU
import Eviction.LRU
import Eviction
import kotlinx.benchmark.*
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Param
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
class BenchmarkRetrieveCache {

    @Param("100", "200", "500", "1000", "5000", "10000")
    private var capacity: Int = 0

    @Param("LRU", "LRU")
    private var eviction: String = ""

    private lateinit var cache: Cache<Int, String>

    @Setup
    fun setUp() {
        cache = Cache(Cache.Setting(capacity, Eviction.valueOf(eviction)))
        for (i in 1..10)
            cache.add(i, "$i")
    }

    @Benchmark
    fun retrieve10Benchmark() {
        for (i in 1..10)
            cache.retrieve(i)
    }
}