package random

class Crossword(val rows: Int, val columns: Int) {

    val crossword = Array(rows) { Array(columns) { '0' } }
    val uLocations = mutableListOf<Pair<Int, Int>>()
    val expectedNextLetter = mapOf('U' to 'N', 'N' to 'I', 'I' to 'O', 'O' to 'N')
    private fun input() {

        for (i in 0..(rows - 1)) {
            val line = readLine()!!.toCharArray()
            for (j in 0..(columns - 1)) {
                crossword[i][j] = line[j]
                if(line[j] == 'U')
                    uLocations.add(Pair(i, j))
            }
        }
    }

    fun wordCount(){
        input()
        var count = 0
        println(uLocations)
        val directions = listOf(
            Pair(-1, -1),
            Pair(0, 1),
            Pair(0, -1),
            Pair(1, 0),
            Pair(-1, 0),
            Pair(1, 1),
            Pair(-1, 1),
            Pair(1, -1)
            )

        uLocations.forEach { uCoordinates ->

            directions.forEach {
                val flag = isWordFormed(
                    uCoordinates.first,
                    uCoordinates.second,
                    it.first,
                    it.second,
                    crossword[uCoordinates.first][ uCoordinates.second],
                    "U"
                )
                if(flag)
                    count ++
            }
        }

        println(count)

    }

    private fun isWordFormed(
        row: Int,
        column: Int,
        rowIncrementor: Int,
        columnIncrementor: Int,
        letter: Char,
        word: String
    ): Boolean {
        if (word == "UNION")
            return true
        val nextRow = row + rowIncrementor
        val nextColumn = column + columnIncrementor
        if(nextRow >= 0 && nextColumn >= 0 && nextRow < rows && nextColumn < columns){
            if(crossword[nextRow][nextColumn] == expectedNextLetter[letter])
                return isWordFormed(
                    nextRow,
                    nextColumn,
                    rowIncrementor,
                    columnIncrementor,
                    crossword[nextRow][nextColumn],
                    word+crossword[nextRow][nextColumn]
                )
        }
        return false
    }
}

fun main() {
    val cases = readLine()!!.toInt()

    for (i in 1..cases) {
        val arraySizeString = readLine()!!.split(" ")
        val rows = arraySizeString[0].toInt()
        val columns = arraySizeString[1].toInt()
        val crossword = Crossword(rows, columns)
        crossword.wordCount()
    }

}