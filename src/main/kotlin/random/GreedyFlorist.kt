package random

import java.util.*

class GreedyFlorist {
    fun getMinimumCost(people: Int, cost: Array<Int>): Int {

        val flowCount = IntArray(people){0}
        var index = 0
        var minimumCost = 0
        for (currentCost in cost.sortedArray().reversedArray()){

            if (index == people){
                index = 0
            }

            minimumCost += (flowCount[index] + 1) * currentCost
            flowCount[index] = (flowCount[index] + 1)
            index++
        }

        return minimumCost
    }
}

fun main() {
    val scan = Scanner(System.`in`)

    val nk = scan.nextLine().split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

    val c = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val greedyFlorist = GreedyFlorist()

    val minimumCost = greedyFlorist.getMinimumCost(k, c)

    println(minimumCost)
}