package dataStructures

class AlienDictionaryWithArray {
    private var order: String = ""

    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        this.order = order
        for (i in 0 until words.size - 1) {
            if(!isSorted(words[i], words[i+1]))
                return false
        }

        return true

    }

    private fun isSorted(word1: String, word2: String): Boolean {

        var checkingIndex = 0

        while (word1.toCharArray()[checkingIndex] == word2.toCharArray()[checkingIndex]){
            checkingIndex++
            if (checkingIndex >= word1.length ||  checkingIndex >= word2.length){
                return when{
                    word1.length == word2.length -> true
                    word2.length > word1.length -> true
                    else -> false
                }
            }

        }

        val currentLetter1 = word1.toCharArray()[checkingIndex]
        val currentLetter2 = word2.toCharArray()[checkingIndex]

        if (order.indexOf(currentLetter2) < order.indexOf(currentLetter1))
            return false

        return true

    }
}

fun main() {
    val words = readLine()!!.split(",").toTypedArray()

    val order = readLine()!!

    val alienDictionaryWithArray = AlienDictionaryWithArray()
    val result = alienDictionaryWithArray.isAlienSorted(words, order)

    println(result)
}