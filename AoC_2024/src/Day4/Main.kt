package Day4

import java.io.BufferedReader
import java.io.File
import java.nio.file.Paths

fun main() {
    val input = readFile()
    partTwo(input)
}

fun partOne(input: String) {
    var count = 0
    val lines = input.lines()
    lines.indices.forEach { lineIndex ->
        val line = lines[lineIndex]
        line.indices.forEach { charIndex ->
            val lastLineIndex = line.length - 1
            val char = line[charIndex]
            if (char == 'X') {
                if (charIndex > 2) {
                    //check to Left
                    if (line[charIndex - 1] == 'M' && line[charIndex - 2] == 'A' && line[charIndex - 3] == 'S') {
                        count++
                    }
                }
                if (charIndex < lastLineIndex - 2) {
                    //check to the Right
                    if (line[charIndex + 1] == 'M' && line[charIndex + 2] == 'A' && line[charIndex + 3] == 'S') {
                        count++
                    }
                }
                if (lineIndex > 2) {
                    //check to the Top
                    if(lines[lineIndex-1][charIndex] == 'M' && lines[lineIndex-2][charIndex] == 'A' && lines[lineIndex-3][charIndex] == 'S'){
                        count++
                    }
                    if(charIndex < lastLineIndex - 2){
                        //check Bottom Right
                        if(lines[lineIndex-1][charIndex+1] == 'M' && lines[lineIndex-2][charIndex+2] == 'A' && lines[lineIndex-3][charIndex+3] == 'S'){
                            count++
                        }
                    }
                    if (charIndex > 2) {
                        //check Bottom Left
                        if(lines[lineIndex-1][charIndex-1] == 'M' && lines[lineIndex+-2][charIndex-2] == 'A' && lines[lineIndex-3][charIndex-3] == 'S'){
                            count++
                        }
                    }

                }
                if (lineIndex < lines.count() - 3) {
                    //check to the Bottom
                    if(lines[lineIndex+1][charIndex] == 'M' && lines[lineIndex+2][charIndex] == 'A' && lines[lineIndex+3][charIndex] == 'S'){
                        count++
                    }
                    if(charIndex < lastLineIndex - 2){
                        //check Bottom Right
                        if(lines[lineIndex+1][charIndex+1] == 'M' && lines[lineIndex+2][charIndex+2] == 'A' && lines[lineIndex+3][charIndex+3] == 'S'){
                            count++
                        }
                    }
                    if (charIndex > 2) {
                        //check Bottom Left
                        if(lines[lineIndex+1][charIndex-1] == 'M' && lines[lineIndex+2][charIndex-2] == 'A' && lines[lineIndex+3][charIndex-3] == 'S'){
                            count++
                        }
                    }
                }



            }
        }

    }
    println(count)
}

fun partTwo(input: String){
    var count = 0
    val lines = input.lines()
    lines.indices.forEach { lineIndex ->
        val line = lines[lineIndex]
        line.indices.forEach { charIndex ->
            val char = line[charIndex]
            if (char == 'A') {
                if(lineIndex > 0 && charIndex > 0 && lineIndex < lines.count() - 1 && charIndex < line.length - 1){
                    var masCount = 0
                    if (lines[lineIndex - 1][charIndex-1]  == 'M' && lines[lineIndex + 1][charIndex+1] == 'S')masCount++
                    if (lines[lineIndex - 1][charIndex+1]  == 'M' && lines[lineIndex + 1][charIndex-1] == 'S')masCount++
                    if (lines[lineIndex + 1][charIndex-1]  == 'M' && lines[lineIndex - 1][charIndex+1] == 'S')masCount++
                    if (lines[lineIndex + 1][charIndex+1]  == 'M' && lines[lineIndex - 1][charIndex-1] == 'S')masCount++
                    if(masCount == 2){
                        count++
                    }
                }
            }
        }

    }
    println(count)
}

fun readFile(): String {
    val path = Paths.get("").toAbsolutePath().toString() + "/AoC_2024/src/Day4/input.txt"
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    return inputString
}