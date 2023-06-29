import java.util.Stack
/************************************************************
 *  Name:         Alex Cruz
 *  Date:         6/26/23
 *  Assignment:
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/


fun main() {
    val solutions = Solutions()

//    val s = "(("
    val s = "(){}}{"
    println(solutions.isValid(s))


}

class Solutions {
    fun isValid(s: String): Boolean {
        var newStack = Stack<String>()

        var openOne = 0
        var closeOne = 0
        var openTwo = 0
        var closeTwo = 0
        var openThree = 0
        var closeThree = 0
        for (c in s) {
            when(c.toString()) {
                "(" -> openOne++
                ")" -> closeOne++
                "[" -> openTwo++
                "]" -> closeTwo++
                "{" -> openThree++
                "}" -> closeThree++
            }
            newStack.push(c.toString())

        }
        if (openOne != closeOne || openTwo != closeTwo || openThree != closeThree) {
            return false
        }else if (s.endsWith("(") || s.endsWith("[") || s.endsWith("{")) {
            return false
        }

        for (i in s) {
            when(i.toString()){
                "(" ->  newStack.push(i.toString())
                ")" ->
                    if (newStack.peek() == "(") {
                        newStack.pop()
                    }else{
                        return false
                    }
                "[" ->  newStack.push(i.toString())
                "]" ->
                    if (newStack.peek() == "["){
                        newStack.pop()
                    }else{
                        return false
                    }
                "{" ->  newStack.push(i.toString())
                "}" ->
                    if (newStack.peek() == "{"){
                        newStack.pop()
                    }else{
                        return false
                    }
            }
        }

        return true
    }
    }



