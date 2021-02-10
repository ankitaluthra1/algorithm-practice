package dp

import kotlin.math.min

data class Cache(val noOfFloors: Int, val usedEggs: Int)

class EggDrop {
    var totalEggs: Int = 0
    var totalFloors: Int = 0
    val minCache = mutableMapOf<Cache, Int>()

    fun superEggDrop(K: Int, N: Int): Int {
        totalEggs = K
        totalFloors = N
        val start = System.currentTimeMillis()
        val minMoves = getMinMoves(1, N, 0)
        println("${(System.currentTimeMillis() - start) / 1000} sec")
        return minMoves
    }

    fun getMinMoves(start: Int, end: Int, usedEggs: Int): Int {
        if (totalEggs - usedEggs == 1) {
            return end - start + 1
        }

        if (start == end) {
            return 1
        }
        if (start > end) {
            return 0
        }

        val cacheKey = Cache(end - start +1 , usedEggs)
        if (minCache.containsKey(cacheKey)) {
            return minCache[cacheKey]!!
        }

        var minMoves = Int.MAX_VALUE

        for (i in start..end) {

            val breaks = 1 + getMinMoves(start, i - 1, usedEggs + 1)

            val notBreaks = 1 + getMinMoves(i + 1, end, usedEggs)

            val moves: Int = maxOf(breaks, notBreaks)
            minMoves = min(minMoves, moves)
        }

        minCache.put(cacheKey, minMoves)
        return minMoves
    }
}

fun main() {
    val eggDrop = EggDrop()
    val eggs = readLine()!!.toInt()
    val floors = readLine()!!.toInt()

    val moves = eggDrop.superEggDrop(eggs, floors)

    println(moves)
}
