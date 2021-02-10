package random

import java.math.BigInteger

val cache = mutableMapOf<Int, BigInteger>()

fun factorial(value: Int): BigInteger {

    if (value == 0 || value == 1)
        return 1.toBigInteger()

    var result: BigInteger = 1.toBigInteger()

    for (i in (1..value).reversed()) {
        val cachedResult = cache[i]

        if (cachedResult != null) {
            result *= cachedResult
            break
        }
        result *= i.toBigInteger()
    }
    cache[value] = result
    return result
}

fun main() {
    val cases = readLine()!!.toInt()
    val inputList = mutableListOf<Int>()
    for (i in 1..cases) {
        inputList.add(readLine()!!.toInt())
    }

    inputList.parallelStream().map {
        val output = (factorial(it).rem((Math.pow(10.toDouble(), 9.0) + 7).toBigDecimal().toBigInteger()))
        output
    }.sequential().forEach {
        println(it)
    }


}
