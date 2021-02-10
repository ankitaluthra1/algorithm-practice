package glassdoorQuestions.trasnferwise

class TransferLinkSubset {

    fun getLinkedPaymentsPair(transfers: MutableList<Int>, sum: Long): MutableList<Int> {
        val sortedList = transfers.sorted()
        val start = 0
        val end = sortedList.size - 1

        val linkedTransfers = mutableListOf<Int>()
        var currentSum: Long
        while (start <= end) {
            currentSum = sortedList[start].toLong() + sortedList[end]
            if (currentSum == sum) {
                linkedTransfers.add(sortedList[start])
                linkedTransfers.add(sortedList[end])
                break
            }
        }
        return linkedTransfers
    }

    fun getLinkedPayments(
        start: Int,
        totalTransfers: List<Int>,
        currentTransfers: MutableList<Int>,
        previousSum: Long,
        totalSum: Long
    ): List<Int> {

        if (totalSum == previousSum)
            return currentTransfers

        for (i in start until totalTransfers.size) {
            if (previousSum + totalTransfers[i] == totalSum) {
                currentTransfers.add(totalTransfers[i])
                return currentTransfers
            }

            if (previousSum + totalTransfers[i] < totalSum) {

                currentTransfers.add(totalTransfers[i])
                val result = getLinkedPayments(
                    i + 1,
                    totalTransfers,
                    currentTransfers,
                    previousSum + totalTransfers[i],
                    totalSum
                )
                if (!result.isEmpty())
                    return result
                currentTransfers.removeAt(currentTransfers.size - 1)

            }
        }
        return emptyList()

    }


}

fun main() {
    val sum = readLine()!!.toLong()
    val numberOfTransfers = readLine()!!.toInt()

    val transfers = mutableListOf<Int>()
    for (i in 0 until numberOfTransfers) {
        transfers.add(readLine()!!.toInt())
    }

    val trasferLinkSubset = TransferLinkSubset()
    val linkedPayments = trasferLinkSubset.getLinkedPayments(
        0,
        transfers,
        mutableListOf(),
        0,
        sum
    )

    if (linkedPayments.size == 0)
        println("No Solution")
    else
        println(linkedPayments.joinToString(" "))
}