package dp

fun main() {
    val cases = readLine()!!.toInt()
    for (i in 0 until cases) {
        val n = readLine()!!.toInt()
        val maxSubarray = MaxSubarray(n)
        maxSubarray.input()
        val x = maxSubarray.getMaxSubArraySum()
        val y = maxSubarray.printMaxSubSequenceSum()
        println("$x $y")

    }

}

class MaxSubarray(val n: Int) {

    lateinit var input: Array<Long>
    var cache = mutableMapOf<Int, Long>()

    fun input() {
        input = readLine()!!.split(" ").map { it.toLong() }.toTypedArray()
    }

    fun maxSum(start: Int): Long {
        if (start == input.size - 1) {
            cache.put(start, input[start])
            return input[start]
        }
        val maxPreviousSum = maxOf(input[start], input[start] + maxSum(start + 1))
        cache.put(start, maxPreviousSum)
        return maxPreviousSum
    }

    fun getMaxSubArraySum(): Long {
        maxSum(0)

        val maxSum=  cache.values.max()!!
        return maxSum

    }

    fun printMaxSubSequenceSum(): Long {
        var sum: Long = input[0]
        var index = 0

        for (i in 1 until input.size){
            if (input[i] > sum) {
                sum = input[i]
                index = i
            }
        }
        for (i in 0 until input.size){
            if (i == index)
                continue
            if (input[i] > 0) {
                sum += input[i]
            }
        }

        return sum
    }

}