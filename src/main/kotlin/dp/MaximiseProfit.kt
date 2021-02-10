package dp

import java.math.BigInteger
import kotlin.math.max

fun main() {
    val cases = readLine()!!.toInt()
    for (i in 1..cases) {
        val maximiseProfit = MaximiseProfit()
        maximiseProfit.input()
        maximiseProfit.totalProfit()
    }
}

class MaximiseProfit {
    var days: Int = 0
    var price: IntArray = IntArray(0)
    var cache = mutableMapOf<Int, Int>()
    fun input() {
        days = readLine()!!.toInt()
        val priceList = readLine()!!.split(" ")
        var index = 0
        price = IntArray(days)
        priceList.forEach {
            price[index] = it.toInt()
            index++
        }
    }

    fun calculateMaxProfit(currentIndex: Int): Int {
        if (cache.containsKey(currentIndex))
            return cache[currentIndex]!!
        if(currentIndex == (days-1))
            return price[currentIndex]
        val maxProfit = max(price[currentIndex], calculateMaxProfit(currentIndex+1))
        cache[currentIndex] = maxProfit
        return maxProfit
    }

    fun totalProfit(){
        var totalProft: BigInteger = 0.toBigInteger()
        calculateMaxProfit(0)
        for (i in 0 until days-1){
            if(price[i] != cache[i]){
                totalProft = totalProft + cache[i]!!.toBigInteger() - price[i].toBigInteger()
            }
        }
        println(totalProft)
    }
}