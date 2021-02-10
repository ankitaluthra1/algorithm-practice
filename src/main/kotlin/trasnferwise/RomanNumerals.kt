package trasnferwise

import java.lang.StringBuilder

class RomanNumerals {

    val numberList = mutableMapOf<Int, String>()

    init {
        numberList.put(1, "I");
        numberList.put(4, "IV");
        numberList.put(5, "V");
        numberList.put(9, "IX");
        numberList.put(10, "X");
        numberList.put(40, "XL");
        numberList.put(50, "L");
        numberList.put(90, "XC");
        numberList.put(100, "C");
        numberList.put(400, "CD");
        numberList.put(500, "D");
        numberList.put(900, "CM");
        numberList.put(1000, "M");
    }

    fun getRomanNumber(input: Int): String {

        val numbers = mutableListOf<Int>()
        var newInput = input

        while (newInput > 0) {
            numbers.add(newInput % 10)
            newInput /= 10
        }

        val builder = StringBuilder("")
        var factor = 0.toDouble()

        for (n in numbers) {
            val romanNumeral = convertToRoman((n * Math.pow(10.toDouble(), factor)).toInt())
            builder.insert(0, romanNumeral)
            factor++
        }

        return builder.toString()

    }

    private fun convertToRoman(input: Int): String {

        if (numberList.containsKey(input)) {
            return numberList[input]!!
        }

        val romanNumber = StringBuilder("")
        var newInput = input

        for (n in numberList.keys.sorted().reversed()) {
            if (newInput > n) {

                while (true) {
                    if (newInput - n < 0) {
                        return romanNumber.toString()
                    }
                    romanNumber.append(numberList.get(n))
                    newInput -= n
                }
            }
        }

        throw IllegalArgumentException("No conversion possible for ${input}, converted input ${newInput} and converted roman number so far: ${romanNumber}")

    }

    fun convertToDecimal(roman: String): Int {
        val list = mutableListOf<Char>()
        val map = mutableMapOf<Char, Int>()

        list.add('I')
        list.add('V')
        list.add('X')
        list.add('L')

        map.put('I', 1)
        map.put('V', 5)
        map.put('X', 10)
        map.put('L', 50)
        val romanChars = roman.toCharArray()

        var index = romanChars.size - 1

        val numberList = mutableListOf<Int>()

        while (index > -1) {

            if (index > 0) {
                val currentNumber1 = romanChars[index]
                val currentNumber2 = romanChars[index - 1]

                if (list.indexOf(currentNumber2) < list.indexOf(currentNumber1)) {

                    val number1 = map.get(currentNumber1)!!
                    val number2 = map.get(currentNumber2)!!

                    numberList.add(number1 - number2)
                    index -= 2
                    continue
                }
            }
            numberList.add(map.get(romanChars[index])!!)
            index--
        }

        return numberList.sum()

    }

}

fun main() {

    var input = readLine()!!

    val r = RomanNumerals()
    var result = r.getRomanNumber(input.toInt())

    println(result)

    input = readLine()!!
    val result2 = r.convertToDecimal(input)

    println(result2)

}