package searchSort

class KFrequentWords {

    val wordCount = mutableMapOf<String, Int>()
    var currentIndex = -1
    lateinit var maxHeap: Array<String>

    fun topKFrequent(words: Array<String>, k: Int): List<String> {

        for (word in words) {
            val currentCount = wordCount.getOrDefault(word, 0)
            wordCount[word] = currentCount + 1
        }

        maxHeap = Array(wordCount.keys.size) { "-" }

        wordCount.forEach { key, value ->
            println("count map: ($key, $value)")
            insertInHeap(key)
        }

        println(maxHeap.contentToString())

        val maxKStrings = mutableListOf<String>()


        for (i in 0 until k) {
            maxKStrings.add(maxHeap[0])

            val lastIndex = currentIndex
            maxHeap[0] = maxHeap[lastIndex]
            maxHeap = maxHeap.sliceArray(0 until lastIndex)
            heapifyDown(0)
            currentIndex--
        }
        return maxKStrings
    }

    private fun insertInHeap(word: String) {

        if (currentIndex == -1) {
            currentIndex++
            maxHeap[currentIndex] = word
        } else {
            currentIndex++
            maxHeap[currentIndex] = word

            heapifyUp(currentIndex)
        }
    }

    private fun heapifyUp(index: Int) {
        if (index < 1)
            return
        val parentIndex: Int
        if (index % 2 == 0) {
            parentIndex = (index - 2) / 2
        } else
            parentIndex = (index - 1) / 2

        if (wordCount[maxHeap.get(parentIndex)]!! < wordCount[maxHeap.get(index)]!!) {
            swap(parentIndex, index)
            heapifyUp(parentIndex)
        } else if ((wordCount[maxHeap.get(parentIndex)]!! == wordCount[maxHeap.get(index)]!!) &&
            (maxHeap.get(index) < maxHeap.get(parentIndex))
        ) {
            swap(parentIndex, index)
            heapifyUp(parentIndex)
        }
    }

    private fun heapifyDown(index: Int) {

        val leftIndex = 2 * index + 1
        val rightIndex = 2 * index + 2

        if (rightIndex < maxHeap.size) {

            val leftCount = wordCount[maxHeap[leftIndex]]!!
            val rightCount = wordCount[maxHeap[rightIndex]]!!
            val currentCount = wordCount[maxHeap[index]]!!

            if (leftCount == rightCount) {
                if (leftCount > currentCount) {
                    if (maxHeap[leftIndex] < maxHeap[rightIndex]) {
                        swap(leftIndex, index)
                        heapifyDown(leftIndex)
                        return
                    }
                    else{
                        swap(rightIndex, index)
                        heapifyDown(rightIndex)
                        return
                    }
                }else{
                    if (leftCount == currentCount){
                        if (maxHeap[leftIndex] < maxHeap[rightIndex] && maxHeap[leftIndex] < maxHeap[index]) {
                            swap(leftIndex, index)
                            heapifyDown(leftIndex)
                            return
                        }
                        if(maxHeap[rightIndex] < maxHeap[leftIndex] && maxHeap[rightIndex] < maxHeap[index]){
                            swap(rightIndex, index)
                            heapifyDown(rightIndex)
                            return
                        }
                    }
                }
            }

            if (leftCount > rightCount) {
                if (leftCount > currentCount) {
                    swap(leftIndex, index)
                    heapifyDown(leftIndex)
                    return
                }
                if (leftCount == currentCount && maxHeap[leftIndex] < maxHeap[index]){
                    swap(leftIndex, index)
                    heapifyDown(leftIndex)
                    return
                }
            }
            if (rightCount > currentCount) {
                swap(rightIndex, index)
                heapifyDown(rightIndex)
                return
            }
            if (rightCount == currentCount && maxHeap[rightIndex] < maxHeap[index]) {
                swap(rightIndex, index)
                heapifyDown(rightIndex)
                return
            }
        } else {
            if (leftIndex < maxHeap.size) {
                val leftCount = wordCount[maxHeap[leftIndex]]!!
                val currentCount = wordCount[maxHeap[index]]!!

                if (leftCount > currentCount) {
                    swap(leftIndex, index)
                    heapifyDown(leftIndex)
                }
                else{
                    if (leftCount == currentCount && maxHeap[leftIndex] < maxHeap[index]) {
                        swap(leftIndex, index)
                        heapifyDown(leftIndex)
                    }
                }
            }
        }

    }

    private fun swap(index1: Int, index2: Int) {
        val temp = maxHeap[index1]
        maxHeap[index1] = maxHeap[index2]
        maxHeap[index2] = temp
    }

}

fun main() {
    val input = readLine()!!.split(",")

    val k = readLine()!!.toInt()

    val kFrequentWords = KFrequentWords()
    val result = kFrequentWords.topKFrequent(input.toTypedArray(), k)

    for (s in result) {
        println(s)
    }
}
