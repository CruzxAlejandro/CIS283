import java.util.Stack
import kotlin.concurrent.fixedRateTimer

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         6/21/23
 *  Assignment:
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/



fun main() {
val solution = Solution()

    val s = "()[]{}"
//    val s = "()"
    println(solution.isValid(s))


}


class Solution {
    fun isValid(s: String): Boolean {
        if (s == "") { return true }
        val simplified = s.replace("()", "").replace("{}", "").replace("[]", "")
        if (simplified == s) { return false }
        return isValid(simplified)
    }
}









