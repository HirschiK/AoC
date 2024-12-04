package Day1
import java.io.File
import java.io.BufferedReader
import java.nio.file.Paths

fun main() {
    val input = readFile()
    partTwo(input)
}

fun partOne(input: String){
    var leftList = intArrayOf()
    var rightList = intArrayOf()
    val lines = input.lines()
    var distance = 0
    lines.forEach {
        val leftElement = it.substring(0..4)
        val rightElement = it.substring(8..12)
        leftList += leftElement.toInt()
        rightList += rightElement.toInt()
    }

    leftList.sort()
    rightList.sort()

    leftList.indices.forEach {
        val currentDistance = Math.abs(leftList[it] - rightList[it])
        distance += currentDistance
    }

    print(distance)
}

fun partTwo(input: String){
    var leftList = intArrayOf()
    var rightList = intArrayOf()
    val lines = input.lines()
    var distance = 0
    lines.forEach {
        val leftElement = it.substring(0..4)
        val rightElement = it.substring(8..12)
        leftList += leftElement.toInt()
        rightList += rightElement.toInt()
    }

    leftList.sort()
    rightList.sort()

    leftList.forEach {
        l ->
        var count = 0
        rightList.forEach { r ->
            if (r == l){
                count ++
            }
        }

        distance += count * l

    }

    print(distance)
}

fun readFile(): String {
    val path = Paths.get("").toAbsolutePath().toString() + "/AoC_2024/src/Day1/input.txt.txt"
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    return inputString
}