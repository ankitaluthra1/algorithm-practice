package dataStructures

//in-complete,wrong ans in 8 cases

class MinimumCostGraphMatrix {
    fun connectTwoGroups(cost: List<List<Int>>): Int {

        if (cost.isEmpty())
            return 0

        val colSize = cost[0].size

        val minCostColumns = mutableMapOf<Int, MutableList<Int>>()
        val minCostRows = mutableMapOf<Int, Int>()

        for (i in 0 until cost.size) {
            var minCost = Int.MAX_VALUE
            var colNumber = 0
            for (j in 0 until colSize) {
                if (cost[i][j] < minCost) {
                    minCost = cost[i][j]
                    colNumber = j
                }
            }
            val currentRowMappings = minCostColumns.getOrDefault(colNumber, mutableListOf())
            currentRowMappings.add(i)

            minCostColumns[colNumber] = currentRowMappings
            minCostRows[i] = colNumber
        }

        minCostRows.forEach {
            println("(${it.key}, ${cost[it.key][it.value]})")
        }

        for (k in 0 until colSize) {
            if (!minCostColumns.containsKey(k)) {
                var min = Int.MAX_VALUE
                var rowNumber = 0
                for (i in 0 until cost.size) {
                    val previousCol = minCostRows[i]!!
                    val rowListForPrevCol = minCostColumns[previousCol]!!
                    var decreasingCost = 0
                    if (rowListForPrevCol.size > 1) {
                        decreasingCost = cost[i][previousCol]
                    }

                    val totalCost = cost[i][k] - decreasingCost

                    if (totalCost < min) {
                        min = totalCost
                        rowNumber = i
                    }
                }

                println("For $k column chose $rowNumber")

                val list = mutableListOf<Int>()
                list.add(rowNumber)
                minCostColumns[k] = list

                val previousCol = minCostRows[rowNumber]!!
                val rowListForPrevCol = minCostColumns[previousCol]!!
                if (rowListForPrevCol.size > 1) {
                    rowListForPrevCol.remove(rowNumber)
                    minCostColumns[previousCol] = rowListForPrevCol
                }
            }
        }
        println("------------------------")
        minCostColumns.toSortedMap().forEach {
            println("(${it.key}, ${it.value.toIntArray().contentToString()})")
        }

        var sum = 0

        minCostColumns.forEach {
            for (i in 0 until it.value.size) {
                sum += cost[it.value[i]][it.key]
            }
        }

        return sum
    }
}

fun main() {
    val input = readLine()!!.replace(" ", "").split("],[")
        .map { it.replace("[", "").replace("]", "") }
        .map { it.split(",").map { it.toInt() } }

    val minimumCostGraphMatrix = MinimumCostGraphMatrix()
    val result = minimumCostGraphMatrix.connectTwoGroups(input)

    println(result)
}