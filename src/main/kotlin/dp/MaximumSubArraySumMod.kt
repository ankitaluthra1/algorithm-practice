package dp

import java.math.BigInteger
//Not working
fun main() {

    val cases = readLine()!!.toInt()

    for (i in 0 until cases) {
        val input = readLine()!!.split(" ")
        val n = input[0].toInt()
        val mod = input[1].toLong()
        val maximumSubArraySumMod = MaximumSubArraySumMod(n, mod)
        maximumSubArraySumMod.input()
        maximumSubArraySumMod.findMax()
    }
}

class MaximumSubArraySumMod(val n: Int, val mod: Long) {

    lateinit var input: Array<BigInteger>
    lateinit var prefix: Array<BigInteger>
    lateinit var cacheValue: BigInteger
    var cacheIndex: Int = 0

    fun input() {
        input = readLine()!!.split(" ").map { it.toBigInteger() }.toTypedArray()
        var sum = 0.toBigInteger()
        prefix = Array(input.size, {0.toBigInteger()})
        for (i in 0 until input.size){
            sum += input[i]
            prefix[i] = sum%mod.toBigInteger()
        }
    }

    fun maxSum(start: Int): BigInteger {
        println(cacheIndex)
        if (start == prefix.size - 1) {
            cacheIndex = start
            cacheValue = input[start]
            return input[start]
        }
        val maxPreviousSum = maxOf(input[start], input[start] + maxSum(start + 1))
        cacheIndex = start
        cacheValue = maxPreviousSum
        return maxPreviousSum
    }

    fun findMax(){
        maxSum(0)
        println(cacheIndex)
        println(prefix[cacheIndex])
    }
}