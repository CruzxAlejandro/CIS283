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

val preFix = "CIS"
val classNum = 282
val pattern = """^(.*${preFix}.*${classNum}.*)\w""".toRegex()
//val pattern = """.*?[a-z]{1,6}.*\d{3}.*""".toRegex()
//.*?[a-z]{1,6}.*\d{3}.*
//.*?[a-z{1,6}].\d{3}.*

fun main() {
    val lines = File(fileName).readLines()
    for(i in 0..(lines.size - 1)) {
        val classMatch = pattern.find(lines[i])
        if (classMatch != null){
            println(classMatch!!.value)
        }
    }
}