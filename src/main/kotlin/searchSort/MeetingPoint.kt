package searchSort

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    //getConsoleInput()
    getFileInput()
}

private fun getFileInput() {
    val file = File("/Users/ankital/Projects/sample-projects/algos/src/main/resources/input.txt").readLines()
    val cases = file[0].trim().toInt()

    val coordinates = Array(cases) { Pair(0, 0) }

    for (i in 1..cases) {
        val coordinatesInput = file[i].trim().split(" ")
        coordinates[i - 1] = Pair(coordinatesInput[0].toInt(), coordinatesInput[1].toInt())
    }

    val result = MeetingPoint().solve(coordinates)

    println(result)
}

private fun getConsoleInput() {
    val cases = readLine()!!.trim().toInt()

    val coordinates = Array(cases) { Pair(0, 0) }

    for (i in 0 until cases) {
        val coordinatesInput = readLine()!!.split(" ")
        coordinates[i] = Pair(coordinatesInput[0].toInt(), coordinatesInput[1].toInt())
    }

    val result = MeetingPoint().solve(coordinates)

    println(result)
}

class MeetingPoint {
    fun solve(coordinates: Array<Pair<Int, Int>>): Long {

        var x1 = coordinates[0].first
        var y1 = coordinates[0].second
        var x2 = coordinates[0].first
        var y2 = coordinates[0].second

        for (i in 1 until coordinates.size) {
            x1 = minOf(x1, coordinates[i].first)
            y1 = minOf(y1, coordinates[i].second)
            x2 = maxOf(x2, coordinates[i].first)
            y2 = maxOf(y2, coordinates[i].second)
        }

        val medianPair = Pair((x1.toDouble() + x2) / 2, (y1.toDouble() + y2) / 2)

        println("median pair: $medianPair")

        coordinates.sortBy {
            val minXDistance = it.first - medianPair.first
            val minYDistance = it.second - medianPair.second
            Math.sqrt(Math.pow(minXDistance.toDouble(), 2.0)+ Math.pow(minYDistance.toDouble(), 2.0))
        }

        val midPairs = coordinates.slice(0..1)

        var minTotalDistance = Long.MAX_VALUE
        for (j in 0 until midPairs.size) {
            var totalDistance = 0.toLong()
            for (i in 0 until coordinates.size) {
                var xDistance = coordinates[i].first - midPairs[j].first
                var yDistance = coordinates[i].second - midPairs[j].second
                totalDistance += max(Math.abs(xDistance), Math.abs(yDistance))
            }

            minTotalDistance = min(minTotalDistance, totalDistance)
        }
        return minTotalDistance

    }

}