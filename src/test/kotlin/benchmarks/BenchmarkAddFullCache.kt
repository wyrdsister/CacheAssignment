package benchmarks

import Cache
import Eviction
import kotlinx.benchmark.*
import kotlinx.benchmark.Param
import org.openjdk.jmh.annotations.Fork
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
class BenchmarkAddFullCache {

    @Param("100", "200", "500", "1000", "5000", "10000")
    private var capacity: Int = 10

    @Param("LRU")
    private var eviction: String = "LFU"

    private lateinit var cache: Cache<Int, String>

    @Setup
    fun setUp() {
        cache = Cache(Cache.Setting(capacity, Eviction.valueOf(eviction)))
        for (i in 1..capacity)
            cache.add(i, "$i")
    }

    @Benchmark
    fun add10WhenCapacityExceedBenchmark() {
        for (i in 100000..100010)
            cache.add(i, "$i")
    }
}