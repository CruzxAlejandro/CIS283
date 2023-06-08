package wordsearch
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         6/7/23
 *  Assignment:   Word search final
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/

/*
Directions:          Horizontal, vertical, diagonal-right, diagonal-left (8 total)
                     left,right,up,down,northeast,southeast,northwest,southwest
*/

class Puzzle(var row : Int, var col:Int , var file : String) {
    var puzzle = Array(row){Array(col){"."}}
    var wordFile = File(file).readLines()
    var wordArray = mutableListOf<String>()
    var directionArray = arrayOf("LEFT","RIGHT","UP","DOWN","NE","SE","NW","SW")


    init {
        for (i in wordFile) {
            wordArray.add(i)
        }
    }

    fun createPuzzle() : String {
        var retString = ""
        var rowCounter = 0
        var colCounter = 0
        for (row in puzzle.indices) {
            for(col in puzzle[row].indices) {
                colCounter++
                retString += puzzle[row][col]
                if (colCounter < puzzle[row].size) {
                    retString += " "
                }
            }
            rowCounter++
            if (rowCounter < puzzle[row].size) {
                retString += "\n"
            }
            colCounter = 0
        }
        return retString
    }
    fun displayWords() : String {
        var retString = ""
        wordArray.sortByDescending { it.length }
        var counter = 0
        for (i in wordArray) {
            counter++
            retString += i
            if (counter < wordArray.size) {
            retString += "\n" }
        }
        return retString
    }
    fun randRow() : Int {
        return (0..row).random()
    }
    fun ranCol() : Int {
        return (0..col).random()
    }
    fun randDirection() : String {
        return directionArray[(directionArray.indices.random())]
    }


}

 fun main() {
     val puzzle = Puzzle(45,45,"src/main/kotlin/wordsearch/words.txt")
//     print(puzzle.createPuzzle())
//     print(puzzle.displayWords())
     print(puzzle.randDirection())
 }



