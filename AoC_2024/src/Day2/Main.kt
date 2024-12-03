package Day2

import java.io.BufferedReader
import java.io.File
import java.nio.file.Paths
import kotlin.math.abs

fun main() {
    val input = readFile()
    partTwo(input)
}

fun partOne(input: String){
    val lines = input.lines()
    var safeCount = 0
    lines.forEach {
        line ->
        val reports = line.split("\\s+".toRegex())

        var isIncreasing = true
        var isDecreasing = true
        var distanceSafe = true

        for (index in 0..<(reports.count() - 1)){
            if(reports[index].toInt() > reports[index + 1].toInt()){
                isIncreasing = false
            }
            else if(reports[index].toInt() < reports[index + 1].toInt()){
                isDecreasing = false
            }

            val distance = abs(reports[index].toInt() - reports[index + 1].toInt())
            if(distance > 3 || distance < 1){distanceSafe = false}
        }

        if(distanceSafe && (isIncreasing || isDecreasing)){
            safeCount++
        }
    }
    print(safeCount)
}

fun partTwo(input: String) {
    val lines = input.lines()
    var safeCount = 0
    lines.forEach {
        line ->
        val originalReports = line.split("\\s+".toRegex())

        var reportsextended: MutableList<List<String>> = emptyList<List<String>>().toMutableList()

        originalReports.indices.forEach {
            var copy = originalReports.toMutableList()
            copy.removeAt(it)
            reportsextended.add(copy)
        }

        println(reportsextended)

        run breaking@{
            reportsextended.forEach {
                reports ->
                var isIncreasing = true
                var isDecreasing = true
                var distanceSafe = true

                for (index in 0..<(reports.count() - 1)){
                    if(reports[index].toInt() > reports[index + 1].toInt()){
                        isIncreasing = false
                    }
                    else if(reports[index].toInt() < reports[index + 1].toInt()){
                        isDecreasing = false
                    }

                    val distance = abs(reports[index].toInt() - reports[index + 1].toInt())
                    if(distance > 3 || distance < 1){distanceSafe = false}

                }
                if(distanceSafe && (isIncreasing || isDecreasing)){
                    safeCount++
                    return@breaking;
                }


            }
        }
    }
    print(safeCount)
}

fun readFile(): String {
    val path = Paths.get("").toAbsolutePath().toString() + "/AoC_2024/src/Day2/input.txt"
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    return inputString
}