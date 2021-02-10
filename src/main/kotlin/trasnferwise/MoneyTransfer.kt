package trasnferwise

import kotlin.math.roundToInt

class MoneyTransfer(val routes: Map<String, List<ConversionRoute>>) {

    fun getMaxAmount(src: String) {

        var totalMoney = 0.toDouble()

        val filteredRoutes = routes.getValue(src)
        for (i in 0 until filteredRoutes.size) {
            if (filteredRoutes[i].destination == "SGD") {
                totalMoney += filteredRoutes[i].maxLimit * filteredRoutes[i].exchangeRate
            }else {
                totalMoney += getAmountFor(filteredRoutes[i].destination, filteredRoutes[i].maxLimit * filteredRoutes[i].exchangeRate)
            }
        }

        println(totalMoney.roundToInt())

    }

    private fun getAmountFor(source: String, maxLimit: Double): Double {
        if (routes.containsKey(source)) {
            var moneyTransfer = Double.MIN_VALUE
            val list = routes.getValue(source)
            for (i in 0 until list.size) {
                if (list[i].destination == "SGD") {
                    moneyTransfer = maxOf(moneyTransfer, minOf(list[i].maxLimit, maxLimit) * list[i].exchangeRate)
                } else {
                    val convertedAmount = getAmountFor(
                        list[i].destination,
                        list[i].exchangeRate * minOf(list[i].maxLimit, maxLimit)
                    )
                    moneyTransfer = maxOf(
                        convertedAmount, moneyTransfer
                    )
                }
            }

            return moneyTransfer
        }
        return 0.toDouble()
    }

}

class ConversionRoute(val destination: String, val exchangeRate: Double, val maxLimit: Double)

fun main() {

    val routesCount = readLine()!!.toInt()

    val routeMap = mutableMapOf<String, List<ConversionRoute>>()

    for (i in 0 until routesCount) {
        val input = readLine()!!.split(",")
        val currentKey = input.get(0)
        val currenList = routeMap.getOrDefault(currentKey, listOf()).toMutableList()
        val route = ConversionRoute(input.get(1), input.get(2).toDouble(), input.get(3).toDouble())
        currenList.add(route)
        routeMap.put(currentKey, currenList)
    }

    val moneyTransfer = MoneyTransfer(routeMap)
    moneyTransfer.getMaxAmount("USD")

}