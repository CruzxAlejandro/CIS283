package leetcode

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         7/12/23
 *  Assignment:
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/



fun main() {
    var num = 6500
    var test = RomanNumerals()

    println(test.intToRoman(num))

}


class RomanNumerals {
    fun intToRoman(num: Int): String {
        var retString = ""

        var mutNum = num

        if (mutNum / 1000 != 0) {
            var m = mutNum / 1000
            for (i in 0 until m) {
                retString += "M"
            }
            mutNum %= 1000
        }

        //---------------------//

        if (mutNum / 500 != 0) {
            if(mutNum / 900 == 1){
                retString += "CM"
                mutNum %= 900
            }
            else {
                    retString += "D"
                    mutNum %= 500

            }
        }

        //---------------------//

        if (mutNum / 100 != 0) {
            if (mutNum / 400 == 1) {
                retString += "CD"
                mutNum %= 400
            }
            else{
                var c = mutNum / 100
                for (i in 0 until c) {
                    retString += "C"
                }
                mutNum %= 100
            }
        }

        if (mutNum / 50 != 0) {
            if (mutNum / 90 == 1) {
                retString += "XC"
                mutNum %= 90
            }
            else{
                retString += "L"
                mutNum %= 50
            }
        }


        if (mutNum / 10 != 0) {
            if (mutNum / 40 == 1) {
                retString += "XL"
                mutNum %= 40
            }
            else{
                var x = mutNum / 10
                for (i in 0 until x){
                    retString += "X"
                }
                mutNum %= 10
            }
        }

        while (mutNum > 0) {
            if (mutNum / 9 == 1) {
                retString += "IX"
                mutNum %= 9
            }
            if (mutNum / 8 == 1) {
                retString += "V"
                mutNum %= 5
            }
            if (mutNum / 5 == 1) {
                retString += "V"
                mutNum %= 5
            }
            return if (mutNum / 4 == 1) {
                retString += "IV"
                retString
            }else{
                for (i in 0 until mutNum) {
                    retString += "I"
                }
                retString
            }

        }

        return retString
    }
}