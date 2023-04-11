
/*
•	….WhaT
•	Most people yell HALT
trapps
Stats
studded
Strap
test
Not
(555) 222-2222
556-33-1234
509-12-2121
Me
•	sling
•	sting
•	stars of 42 lights will be going

*/

fun main() {
    val pattern = """\A\(\d{3}\).\d{3}-\d{4}\Z""".toRegex()

    val stringOne = "(555) 222-2222"
    val stringTwo = "(556) 333-1234"
    val stringThree = "(509) 312-2121"
    val stringFour = "That Time..."
//
//    val match = pattern.find(stringOne)
//    println(stringOne.substring(match!!.range))
//    val matchTwo = pattern.find(stringTwo)
//    println(stringTwo.substring(matchTwo!!.range))

    if (pattern.containsMatchIn(stringOne) && pattern.containsMatchIn(stringTwo) && pattern.containsMatchIn(stringThree)){
        println("YES. match found")
    } else {
        println("NO. match not found")
    }


}