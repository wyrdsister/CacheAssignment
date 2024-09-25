# In-Memory Cache with LRU and LFU Eviction Strategies

### Overview
This project implements an in-memory cache with configurable maximum size and eviction strategy. The cache is designed to store objects (key-value pairs) with two supported eviction strategies:

- LRU (Least Recently Used): The cache evicts the item that was used least recently when it reaches the maximum size.
- LFU (Least Frequently Used): The cache evicts the least frequently used item when it reaches the maximum size.

### Key Features
- Configurable maximum size for the cache.
- Two eviction strategies: LRU and LFU.
- In-memory storage of objects (key-value pairs).
- Unit tests demonstrating usage of the cache.

### Assumptions
Only one thread will access the cache, so no thread-safety mechanisms are implemented.

## Project Structure
The project consists of the following key components:

1. Cache Class: A basic class to manage cache operations.
2. ICache Interface: Defines the basic operations (add, retrieve, evict) for the cache.
3. LruCacheImpl: Implements the cache using the Least Recently Used (LRU) eviction strategy.
4. LfuCacheImpl: Implements the cache using the Least Frequently Used (LFU) eviction strategy.
5. Unit Tests: Unit tests for both LRU and LFU caches, demonstrating their usage and verifying functionality.
6. Benchmarks: Performance benchmarks to evaluate the cache's operations.

## How to Use
You can initialize the cache by choosing either the LRU or LFU implementation and defining the maximum capacity. Here’s how to use both caches.

Cache Example:
```
# Create an LRU/LFU cache with a maximum size of 3
val capacity = 3
val cache = Cache(Cache.Setting(capacity, Eviction.LRU))

# Add items to the cache
cache.add(1, "A")
cache.add(2, "B")
cache.add(3, "C")

# Retrieve items from the cache
print(cache.retrieve(1))  # Output: "A"
print(cache.retrieve(2))  # Output: "B"

# Add another item, causing the least recently used item to be evicted
cache.add(4, "D")
print(cache.retrieve(3))  # Output: None (item 3 was evicted)

```

## Installation

Clone this repository to your local machine:
```
git clone https://github.com/wyrdsister/CacheAssignment
```
Navigate to the project directory:
```
cd ./CacheAssignment
```
Install the required dependencies and build the project:
```
./gradlew build
```

## Running the Tests and Benchmarks
To run the unit tests, execute the following command:
```
./gradlew test
```
This will automatically discover and run all unit tests within the project.

To run the benchmarks, execute the following command:
```
./gradlew benchmark
```

## Future Enhancements
- Implement thread safety for concurrent access.
- Improve benchmarks with more extensive performance metrics.

## License
This project is licensed under the MIT License. See the [LICENSE](https://opensource.org/license/mit) file for details.