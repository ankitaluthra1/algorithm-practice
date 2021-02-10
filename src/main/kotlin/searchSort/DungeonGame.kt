package searchSort

import kotlin.math.max
import kotlin.math.min

class DungeonGame(val rows: Int, val cols: Int, val playground: Array<IntArray>) {

    val cache = mutableMapOf<Pair<Int, Int>, Int>()

    private fun getCost(row: Int, col: Int): Int {

        val key = Pair(row, col)
        if (cache.containsKey(key)) {
            return cache[key]!!
        }

        if (row == rows - 1 && col == cols - 1) {
            return playground[row][col]
        }

        if (row == rows - 1) {
            val min = min(playground[row][col], playground[row][col] + getCost(row, col + 1))
            cache[key] = min
            return min
        }

        if (col == cols - 1) {
            val min = min(playground[row][col], playground[row][col] + getCost(row + 1, col))
            cache[key] = min
            return min
        }

        val min = min(playground[row][col], playground[row][col] + max(getCost(row + 1, col), getCost(row, col + 1)))
        cache[key] = min
        return min
    }

    fun getMinHealth(): Int {
        val cost = getCost(0, 0)

        if (cost >= 0)
            return 1

        return Math.abs(cost) + 1
    }

}

class DungeonSolution {
    fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
        val dungeonGame = DungeonGame(dungeon.size, dungeon[0].size, dungeon)

        val result = dungeonGame.getMinHealth()
        println(result)
        return result
    }
}

fun main() {
    val rows = readLine()!!.toInt()
    val cols = readLine()!!.toInt()

    val matrix: Array<IntArray> = Array(rows) { IntArray(cols) }


    for (i in 0 until rows) {
        for (j in 0 until cols) {
            matrix[i][j] = readLine()!!.toInt()
        }
    }

    val dungeonGame = DungeonGame(rows, cols, matrix)

    val result = dungeonGame.getMinHealth()
    println(result)

}