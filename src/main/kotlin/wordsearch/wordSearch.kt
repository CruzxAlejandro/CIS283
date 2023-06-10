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
    var puzzleBoard = Array(row){Array(col){"."}}
    var wordFile = File(file).readLines()
    var wordArray = mutableListOf<String>()
//    var directionArray = arrayOf("N","E","S","W","NE","SE","NW","SW")
    var directionArray = arrayOf("N","E","S","W")


    init {
        var whiteSpace = """\s""".toRegex()
        for (i in wordFile) {
            wordArray.add(i.replace(whiteSpace,"").uppercase())
        }
    }

    fun testPlace(word:String, randRow: Int, randCol: Int, direction: String) : Boolean {
        var wordPlaced = true
        var currentCol = randCol
        var currentRow = randRow
        var startSearch = false
        when(direction) {
            "S" -> if ((randRow - word.length) >= row - 1) {
                startSearch = true
            }
            "N" -> if ((randRow - word.length) >= 0) {
                startSearch = true
            }
            "E" -> if ((randCol + word.length <= col - 1)) {
                startSearch = true
            }
            "W" -> if ((randCol - word.length) >= 0) {
                startSearch = true
            }
        }

        if (startSearch) {
            for (letters in word) {
                if (puzzleBoard[currentRow][currentCol] == "." || puzzleBoard[currentRow][currentCol] == letters.toString()) {
                    when (direction) {
                        "E" -> currentCol++
                        "N" -> currentRow--
                        "S" -> currentRow++
                        "W" -> currentCol--
                    }
                } else {
                    wordPlaced = false
                }
            }
        } else{
            wordPlaced = false
        }

        return wordPlaced
    }

    fun placeWord(word: String, randRow: Int, randCol: Int, direction: String) {
        var currentCol = randCol
        var currentRow = randRow
        for (letters in word) {
            if (puzzleBoard[currentRow][currentCol] == "." || puzzleBoard[currentRow][currentCol] == letters.toString()) {
                when (direction) {
                    "E" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentCol++
                    }
                    "N" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentRow--
                    }
                    "S" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentRow++
                    }
                    "W" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentCol--
                    }
                }
            }
        }
    }

    fun buildPuzzle() {

         wordArray.sortByDescending { it.length }
        for (word in wordArray) {
            var randRow = randRow()
            var randCol = randCol()
            var direction = randDirection()
            var flagOne = false
            var flagTwo = false

            do {
                if (word.length + randCol <= col - 1 && word.length + randRow <= row - 1) {
                    while (!flagTwo) {
                        if (testPlace(word,randRow,randCol,direction)) {
                            placeWord(word,randRow,randCol,direction)
                            flagTwo = true
                        }else{
                            direction = randDirection()
                            randRow = randRow()
                            randCol = randCol()
                        }
                    }
                    flagOne = true
                } else {
                    randRow = randRow()
                    randCol = randCol()
                }

            }while (!flagOne)
        }
    }
    fun createPuzzle() : String {
        buildPuzzle()
        var retString = ""
        var rowCounter = 0
        var colCounter = 0
        for (row in puzzleBoard.indices) {
            for(col in puzzleBoard[row].indices) {
                colCounter++
                retString += puzzleBoard[row][col]
                if (colCounter < puzzleBoard[row].size) {
                    retString += " "
                }
            }
            rowCounter++
            if (rowCounter < puzzleBoard[row].size) {
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
    fun randCol() : Int {
        return (0..col).random()
    }
    fun randDirection() : String {
        return directionArray[(directionArray.indices.random())]
    }


}

 fun main() {
     val puzzle = Puzzle(45,45,"src/main/kotlin/wordsearch/words.txt")
     println(puzzle.createPuzzle())
 }



