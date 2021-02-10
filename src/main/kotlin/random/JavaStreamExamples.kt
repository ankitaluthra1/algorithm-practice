package random

fun main() {

    val numbers = listOf(4, 10, 4, 6, 9, 3, 5, 9, 10, 3)
    val sum = numbers.stream().limit(4).reduce{x, y -> x+y}.get()

    println("Average ${sum/4}")

}