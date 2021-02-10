package random

class LRUCache(val capacity: Int) {

    var currentSize = 0
    val cache = mutableMapOf<Int, Int>()

    val accessedList = mutableListOf<Int>()

    fun get(key: Int): Int {

        if (cache.containsKey(key)) {
            accessedList.remove(key)
            accessedList.add(key)
            return cache[key]!!
        }

        return -1
    }

    fun put(key: Int, value: Int) {

        if (cache.containsKey(key)) {
            accessedList.remove(key)
            accessedList.add(key)
            cache[key] = value
            return
        }

        if (currentSize == capacity) {

            val removableKey = accessedList.removeAt(0)
            cache.remove(removableKey)
            accessedList.add(key)
            cache[key] = value
            return
        }

        currentSize++
        accessedList.add(key)
        cache[key] = value
    }

}

fun main() {

    val capacity = readLine()!!.toInt()

    val lRUCache = LRUCache(capacity)

    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    lRUCache.get(1);    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);

}