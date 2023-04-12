package regexsearch
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         4/11/23
 *  Assignment:   Text Search with Regular Expressions
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/

val fileName = "src/main/kotlin/regexsearch/enrollment.txt"
/*
        val pattern = """\A.{10}${departmentChoice}.*${classNum}.*""".toRegex()
        val patternTwo = """\A\s{11}.*$""".toRegex()
        print("Enter Class Number: ")
        var classNum = readLine()
        val pattern = """\A.{10}${departmentChoice}.{1,3}${classNum}.*""".toRegex()
        val patternTwo = """\A\s{11}.*$""".toRegex()
 */

fun main() {
    do {
        print("Enter Department: ")
        var departmentChoice = readLine()
        if (departmentChoice != "EXIT") {
            print("Enter Class Number: ")
            var classNum = readLine()
            val pattern = """\A.{10}${departmentChoice}\s{1,4}${classNum}.*""".toRegex()
            val patternTwo = """\A\s{11}\S.*|\A\s{58}\S.*""".toRegex()
            val lines = File(fileName).readLines()
            for (i in 0..(lines.size - 1)) {
                val classMatch = pattern.find(lines[i])
                if (classMatch != null) {
                    println(classMatch!!.value)
                    var classInfo = patternTwo.find(lines[i + 1])
                    if (classInfo != null) {
                        println(classInfo!!.value)
                        var counter = 2
                        var flag = false
                        while (!flag) {
                            var nextMatch = patternTwo.find(lines[i + counter])
                            if (nextMatch != null) {
                                println(nextMatch!!.value)
                                counter++
                            } else {
                                counter = 2
                                flag = true
                            }
                        }
                    }
                }
            }
        }
    } while(departmentChoice != "EXIT" )
}
