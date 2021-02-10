package dataStructures

class CheapestFlight {

    data class CacheKey(val src: Int,val dst: Int, val k: Int)

    lateinit var graph: Array<MutableList<Pair<Int, Int>>>

    val cache = mutableMapOf<CacheKey, Int>()

    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {

        graph = Array(n) { mutableListOf<Pair<Int, Int>>()}

        for (i in 0 until flights.size){
            val currentNode = flights[i]
            val list = graph[currentNode[0]]
            list.add(Pair(currentNode[1], currentNode[2]))
        }

        return cheapestFlight(src, dst, K)
    }

    private fun cheapestFlight(src: Int, dst: Int, k: Int): Int {
        val key = CacheKey(src,dst, k)

        if (cache.containsKey(key)){
            return cache[key]!!
        }

        if (k == 0) {
            val neighbors = graph[src]
            var min = Int.MAX_VALUE
            neighbors.forEach {
                if (it.second < min && it.first == dst) {
                    min = it.second
                }
            }
            if (min == Int.MAX_VALUE) {
                cache[key] = -1
                return -1
            }
            cache[key] = min
            return min
        }

        var min = Int.MAX_VALUE

        val neighbors = graph[src]

        for (i in 0 until neighbors.size) {
            if (neighbors[i].first == dst) {
                min = minOf(min, neighbors[i].second)
            } else {
                val currentDistance = neighbors[i].second
                val neighborFlightCost = cheapestFlight(neighbors[i].first, dst, k - 1)
                if (neighborFlightCost == -1)
                    continue
                if (neighborFlightCost + currentDistance < min) {
                    min = currentDistance + neighborFlightCost
                }
            }
        }

        if (min == Int.MAX_VALUE) {
            cache[key]=-1
            return -1
        }
        cache[key] = min
        return min
    }

}

fun main() {

    val cheapestFlight = CheapestFlight()

    val input = readLine()!!.replace(" ", "").split("],[")
        .map { it.replace("[", "").replace("]", "") }
        .map { it.split(",").map { it.toInt() }.toIntArray() }.toTypedArray()

    val result = cheapestFlight.findCheapestPrice(17, input, 13, 4, 13)

    println(result)
}