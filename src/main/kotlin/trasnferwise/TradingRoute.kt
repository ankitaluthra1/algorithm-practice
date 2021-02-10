package trasnferwise

class TradingRoute(val currencyMap: MutableMap<String, List<CurrencyRoute>>) {
    fun getMaxAmountTradingRoute(fromCurrency: String, toCurrency: String, checkedCurrencies: List<String> = mutableListOf()): Double {

        if (checkedCurrencies.contains(fromCurrency)){
            return Double.MIN_VALUE
        }

        if (!currencyMap.containsKey(fromCurrency)){
            return Double.MIN_VALUE
        }

        var maxConversionValue = Double
            .MIN_VALUE

        val convertableCurrencyRoutes = currencyMap[fromCurrency]!!

        val currentCheckedList = mutableListOf<String>()
        currentCheckedList.addAll(checkedCurrencies)
        currentCheckedList.add(fromCurrency)
        for (i in 0 until convertableCurrencyRoutes.size) {

            if (convertableCurrencyRoutes[i].toCurrency == toCurrency) {
                maxConversionValue = maxOf(maxConversionValue, convertableCurrencyRoutes[i].exchangeRate)
            }
            val temp = convertableCurrencyRoutes[i].exchangeRate * getMaxAmountTradingRoute(
                convertableCurrencyRoutes[i].toCurrency,
                toCurrency,
                currentCheckedList
            )
            maxConversionValue =
                maxOf(
                    maxConversionValue,
                    temp
                )
        }

        return maxConversionValue

    }


}

class CurrencyRoute(val toCurrency: String, val exchangeRate: Double)

fun main() {

    val input = readLine()!!.split(" ")
    val fromCurrency = input[0]
    val toCurrency = input[1]

    val numberOfRoutes = readLine()!!.toInt()

    val currencyMap = mutableMapOf<String, List<CurrencyRoute>>()

    for (i in 0 until numberOfRoutes) {

        val input = readLine()!!.split(" ")
        val list = currencyMap.getOrDefault(input[0], mutableListOf()).toMutableList()
        list.add(CurrencyRoute(input[1], input[2].toDouble()))
        currencyMap.put(input[0], list)
    }


    val tradingRoute = TradingRoute(currencyMap)
    val result = tradingRoute.getMaxAmountTradingRoute(fromCurrency, toCurrency)

    println("%.5f".format(result))

}