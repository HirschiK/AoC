package Day3

import java.io.BufferedReader
import java.io.File
import java.nio.file.Paths

fun main() {
    val input = readFile()
    partTwo(input)
}

fun partOne(input: String){
    val split = "(mul\\((\\d+,\\d+)\\))".toRegex().findAll(input)
    var result = 0
    split.forEach {
        val curr = it.value
        val firstNum = curr.substring(4,curr.indexOf(","))
        val secondNum = curr.substring(curr.indexOf(",")+1,curr.indexOf(")"))
        if (firstNum.all {it.isDigit() }){
            if(secondNum.all { it.isDigit() }){
                result += firstNum.toInt() * secondNum.toInt()
            }
        }

    }
    //split.forEach { print(it.value + ",") }
    println(result)
}

fun partTwo(input: String){
    val doS = "(?<=do\\(\\))(.*?)(?=don't)".toRegex().findAll(input)
    var result = 0
    doS.forEach {
        doo ->
        println(doo.value)
        val split = "(mul\\((\\d+,\\d+)\\))".toRegex().findAll(doo.value)
        split.forEach {
            mul ->
            //print(mul.value)
            val curr = mul.value
            val firstNum = curr.substring(4,curr.indexOf(","))
            val secondNum = curr.substring(curr.indexOf(",")+1,curr.indexOf(")"))
            if (firstNum.all {it.isDigit() }){
                if(secondNum.all { it.isDigit() }){
                    result += firstNum.toInt() * secondNum.toInt()
                }
            }

        }
    }
    println(result)
}

fun readFile(): String {
    val path = Paths.get("").toAbsolutePath().toString() + "/AoC_2024/src/Day3/input.txt.txt"
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    return inputString
}