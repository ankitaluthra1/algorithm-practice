package dataStructures

class WordSearch {
    lateinit var board: Array<CharArray>

    fun exist(board: Array<CharArray>, word: String): Boolean {

        this.board = board
        val firstChar = word[0]

        val possibleStarts = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until board.size) {
            for (j in 0 until board[i].size) {
                if (firstChar == board[i][j])
                    possibleStarts.add(Pair(i, j))
            }
        }

        for (i in 0 until possibleStarts.size) {
            val start = possibleStarts[i]
            val visited = mutableListOf<Pair<Int, Int>>()
            if (check(start.first, start.second, word, visited)) {
                return true
            }
        }

        return false
    }

    private fun check(i: Int, j: Int, word: String, visited: MutableList<Pair<Int, Int>>): Boolean {
        if (i < 0 || j < 0 || i >= this.board.size || j >= this.board[i].size)
            return false

        val visitedPair = Pair(i, j)
        if (visited.contains(visitedPair))
            return false

        if (word.length == 1) {
            val checked = this.board[i][j] == word.single()
            if (checked)
                visited.add(visitedPair)
            return checked
        }

        val currentCheck = this.board[i][j] == word[0]
        if (!currentCheck)
            return false

        visited.add(visitedPair)
        val remainingWord = word.substring(1)

        return check(i, j - 1, remainingWord, visited.copy()) ||
                check(i + 1, j, remainingWord, visited.copy()) ||
                check(i, j + 1, remainingWord, visited.copy()) ||
                check(i - 1, j, remainingWord, visited.copy())

    }
}

fun <T> MutableList<T>.copy(): MutableList<T> {
    val list = mutableListOf<T>()
    list.addAll(this)
    return list
}

fun main() {

    val inputBoardLine = readLine()!!
    val inputBoard = inputBoardLine.substring(2, inputBoardLine.length - 2).split("],[")

    val board = Array(inputBoard.size) { emptyArray<Char>().toCharArray() }

    for (i in 0 until inputBoard.size) {
        board[i] = inputBoard[i].split(",").map {
            it.removeSurrounding("\"").single()
        }.toCharArray()
    }

    val word = readLine()!!

    val wordSearch = WordSearch()

    val result = wordSearch.exist(board, word)

    println(result)

}