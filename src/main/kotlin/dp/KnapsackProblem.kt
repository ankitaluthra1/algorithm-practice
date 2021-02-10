package dp

import java.io.File

data class ValueWeightMapping(val value: Long, val weight: Int)

class KnapsackProblem(private val listOfBuyers: List<ValueWeightMapping>) {

    private val cache = mutableMapOf<Pair<Int, Int>, ValueWeightMapping>()

    fun getMaximumProfit(index: Int, weightLeft: Int): ValueWeightMapping {
        val key = Pair(index, weightLeft)
        if (cache.containsKey(key)){
            return cache[key]!!
        }

        val lastIndex = listOfBuyers.size - 1
        if (index == lastIndex) {
            if (weightLeft >= listOfBuyers[index].weight) {
                val result = ValueWeightMapping(listOfBuyers[index].value, listOfBuyers[index].weight)
                cache[key] = result
                return cache[key]!!
            } else
                return ValueWeightMapping(0, weightLeft)
        }

        if (listOfBuyers[index].weight > weightLeft) {
            val maximumProfit = getMaximumProfit(index + 1, weightLeft)
            cache[key] = maximumProfit
            return maximumProfit
        } else {
            val currentBoughtValue =  getMaximumProfit(
                index + 1,
                weightLeft - listOfBuyers[index].weight
            )
            val currentBought = ValueWeightMapping(
                listOfBuyers[index].value + currentBoughtValue.value,
                currentBoughtValue.weight + listOfBuyers[index].weight
            )
            val currentNotBought = getMaximumProfit(index + 1, weightLeft)
            if (currentBought.value > currentNotBought.value) {
                cache[key] = currentBought
                return currentBought
            }
            cache[key] = currentNotBought
            return currentNotBought
        }
    }

}

fun main() {
    val firstLine = readLine()!!.split(" ")
    val buyers = firstLine[0].toLong()
    val totalWeight = firstLine[1].toInt()

    val list = mutableListOf<ValueWeightMapping>()
//    for (i in 0 until buyers) {
//        val inputString = readLine()!!.split(" ")
//        dataList.add(ValueWeightMapping(inputString[0].toLong(), inputString[1].toInt()))
//    }

    val inputLines = File("/Users/ankital/Projects/sample-projects/algos/src/main/resources/input.txt").readLines()

       for (i in 0 until buyers) {
        val inputString = inputLines[i.toInt()].split(" ")
        list.add(ValueWeightMapping(inputString[0].toLong(), inputString[1].toInt()))
    }

    val knapsackProblem = KnapsackProblem(list)
    val maxProfit = knapsackProblem.getMaximumProfit(0, totalWeight)

    if (totalWeight == maxProfit.weight)
        println(maxProfit.value)
    else
        println("Got caught!")

}