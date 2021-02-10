package searchSort

class EqualSubsetSum() {

    lateinit var inputArray: IntArray
    val cache = mutableMapOf<Pair<Int, Int>, Boolean>()
    val subSetSumMapping =  mutableListOf<Int>()

    fun canPartition(inputArray: IntArray): Boolean {
        this.inputArray = inputArray
        val totalSum = this.inputArray.sum()
        if(totalSum %2 != 0)
            return false
        val sum = totalSum/2
        val subsetPossible = isSubsetPossible(inputArray.size - 1, sum)
        println(subSetSumMapping)
        return subsetPossible
    }

    fun isSubsetPossible(index: Int, sum: Int): Boolean{
        if (cache.containsKey(Pair(index, sum)))
            return cache[Pair(index, sum)]!!
        if(index == 0){
            if(inputArray[index] == sum) {
                cache[Pair(index, sum)] = true
                subSetSumMapping.add(inputArray[index])
            }
            cache[Pair(index, sum)] = false
            return cache[Pair(index, sum)]!!
        }

        if (inputArray[index] > sum){
            cache[Pair(index, sum)] = isSubsetPossible(index - 1,sum)
            return cache[Pair(index, sum)]!!
        }

        if (inputArray[index] == sum) {
            cache[Pair(index, sum)] = true
            subSetSumMapping.add(inputArray[index])
            return cache[Pair(index, sum)]!!
        }

        val leftOverSum = sum - inputArray[index]
        val subsetPossibleWithCurrentElement = isSubsetPossible(index - 1, leftOverSum)
        if (subsetPossibleWithCurrentElement){
            subSetSumMapping.add(inputArray[index])
        }
        cache[Pair(index, sum)] = subsetPossibleWithCurrentElement || isSubsetPossible(index- 1, sum)
        return cache[Pair(index, sum)]!!
    }

}

fun main() {
    val inputArray = readLine()!!.split(",").map { it.toInt() }.toIntArray()

    val equalSubsetSum = EqualSubsetSum()
    println(equalSubsetSum.canPartition(inputArray))

}