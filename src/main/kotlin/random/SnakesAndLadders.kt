package random

import java.util.*

lateinit var board: MutableMap<Int, MutableList<Pair<Int, Int>>>
lateinit var snakesLaddersCache: MutableMap<Int, Int>
fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
    board = mutableMapOf()
    snakesLaddersCache = mutableMapOf()
    for (i in 1..100) {
        var isLadder = false
        var isSnake = false
        board[i] = mutableListOf()
        for (ladder in ladders) {
            if (ladder[0] == i) {
                board[i]!!.add(Pair(ladder[1], 0))
                isLadder = true
                break
            }
        }
        if (!isLadder) {
            for (snake in snakes) {
                if (snake[0] == i) {
                    board[i]!!.add(Pair(snake[1], 0))
                    isSnake = true
                    break
                }
            }
            if (!isSnake) {
                for (j in 1..6) {
                    if (i + j <= 100)
                        board[i]!!.add(Pair(i + j, 1))
                }
            }
        }

    }
    val minTurns = findShortestPath(1, 100, listOf())
    if (minTurns == Int.MAX_VALUE)
        return -1
    return minTurns
}

fun findShortestPath(start: Int, destination: Int, visited: List<Int>): Int {
    if (snakesLaddersCache.containsKey(start)) {
        return snakesLaddersCache[start]!!
    }

    if (start == destination)
        return 0

    val connectedSquares = board[start]!!

    var minDistance = Int.MAX_VALUE
    for (neighbor in connectedSquares) {
        if (visited.contains(neighbor.first))
            continue
        val newVisitedList = visited.toMutableList()
        newVisitedList.add(neighbor.first)

        if (neighbor.first == destination) {
            minDistance = minOf(minDistance, neighbor.second)
        } else {
            val shortestNeighborPath = findShortestPath(neighbor.first, destination, newVisitedList)
            if (shortestNeighborPath == Int.MAX_VALUE)
                continue
            minDistance = minOf(minDistance, neighbor.second + shortestNeighborPath)
        }
    }

    snakesLaddersCache[start] = minDistance

    return minDistance
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val t = scan.nextLine().trim().toInt()

    for (tItr in 1..t) {
        val n = scan.nextLine().trim().toInt()

        val ladders = Array<Array<Int>>(n, { Array<Int>(2, { 0 }) })

        for (i in 0 until n) {
            ladders[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
        }

        val m = scan.nextLine().trim().toInt()

        val snakes = Array<Array<Int>>(m, { Array<Int>(2, { 0 }) })

        for (i in 0 until m) {
            snakes[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
        }

        val result = quickestWayUp(ladders, snakes)

        println(result)
    }
}