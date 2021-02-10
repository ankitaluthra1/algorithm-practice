package dp

import java.math.BigInteger

fun main() {

    val cases = readLine()!!.toInt()

//    for (i in 0 until cases) {
//        val days = readLine()!!.toLong()
//        val workoutDays = WorkoutDays()
//        val plan = workoutDays.getPlan(days, true)
//
//        println(plan%(Math.pow(10.toDouble(), 9.toDouble()).toBigDecimal().toBigInteger()+7.toBigInteger()))
//    }
    val workoutDays = WorkoutDays()
    for (i in 0 until cases) {
        val days = readLine()!!.toBigInteger()
        val plan =workoutDays.fib(days)
        println(plan%(Math.pow(10.toDouble(), 9.toDouble()).toBigDecimal().toBigInteger()+7.toBigInteger()))
    }

}

class WorkoutDays {

    val goCache = mutableMapOf<Int, BigInteger>()
    val noGoCache = mutableMapOf<Int, BigInteger>()
    val fibCache = mutableMapOf<Int, BigInteger>()

    fun getPlan(days: BigInteger, canGo: Boolean): BigInteger {

        return if (days == 1.toBigInteger()) {
            if (canGo) {
                2.toBigInteger()
            } else {
                1.toBigInteger()
            }
        } else {
            if (canGo) {
                return if (goCache.containsKey(days.hashCode())) {
                    val temp = goCache.get(days.hashCode())!!
                    goCache.remove(days.hashCode())
                    temp
                } else {
                    goCache.put(days.hashCode(), getPlan(days - 1.toBigInteger(), false) + getPlan(days - 1.toBigInteger(), true))
                    goCache.get(days.hashCode())!!
                }
            } else {
                return if (noGoCache.containsKey(days.hashCode())) {
                    val temp = noGoCache.get(days.hashCode())!!
                    noGoCache.remove(days.hashCode())
                    temp
                }
                else {
                    noGoCache.put(days.hashCode(), getPlan(days - 1.toBigInteger(), true))
                    noGoCache.get(days.hashCode())!!
                }
            }
        }
    }

    fun fib(n: BigInteger): BigInteger {
        var i = 1.toBigInteger()
        var first = 1.toBigInteger()
        var result = 2.toBigInteger()
        while (i<n){
            result+=first
            first=result-first
            i++
        }
        return result
    }
}