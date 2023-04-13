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
var departmentChoice = ""
var classNum = ""
var endProgram = false

fun main() {
    while (!endProgram) {
        print("Enter Department: ")
        departmentChoice = readLine()!!
        if (departmentChoice != "EXIT"){
            print("Enter Class Number: ")
            classNum = readLine()!!
            if (classNum != "EXIT") {
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
            else{
                endProgram = true
            }
        }
        else{
            endProgram = true
        }
    }
}