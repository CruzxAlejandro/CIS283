package wordsearch
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         6/7/23
 *  Assignment:   Word search Extra Credit
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/


class Puzzle(var row : Int, var col:Int , var file : String) {
    var puzzleBoard = Array(row){Array(col){"."}}
    var wordFile = File(file).readLines()
    var wordArray = mutableListOf<String>()
    var charArray = mutableListOf<String>()
    var directionArray = arrayOf("N","E","S","W","NE","SE","NW","SW")
    init {
        var whiteSpace = """\s""".toRegex()
        for (i in wordFile) {
            wordArray.add(i.replace(whiteSpace,"").uppercase())
            for (j in i) {
                if (j != ' ') {
                    charArray.add(j.uppercase())
                }
            }
        }

    }
    fun testPlace(word:String, randRow: Int, randCol: Int, direction: String) : Boolean {
        var wordPlaced = true
        var currentCol = randCol
        var currentRow = randRow
        var startSearch = false
        when(direction) {
            "S" -> if ((randRow + word.length) <= row - 1) {
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
            "NE" -> if (((randRow - word.length) >= 0) && ((randCol + word.length <= col - 1)) ){
                startSearch = true
                }
            "SE" -> if (((randRow + word.length) <= row - 1) && ((randCol + word.length <= col - 1))){
                startSearch = true
            }
            "SW" -> if (((randRow + word.length) <= row - 1) && ((randCol - word.length) >= 0) ) {
                startSearch = true
            }
            "NW" -> if(((randRow - word.length) >= 0) && ((randCol - word.length) >= 0)) {
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
                        "NE" ->{
                            currentCol++
                            currentRow--
                        }
                        "SE" -> {
                            currentCol++
                            currentRow++
                        }
                        "SW" -> {
                            currentCol--
                            currentRow++
                        }
                        "NW" -> {
                            currentCol--
                            currentRow--
                        }
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
                    "NE" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentCol++
                        currentRow--
                    }
                    "SE" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentCol++
                        currentRow++
                    }
                    "SW" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentCol--
                        currentRow++
                    }
                    "NW" -> {
                        puzzleBoard[currentRow][currentCol] = letters.toString()
                        currentCol--
                        currentRow--
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
            while (!flagOne) {
                if (testPlace(word,randRow,randCol,direction)) {
                    placeWord(word,randRow,randCol,direction)
                    flagOne = true
                }else{
                    direction = randDirection()
                    randCol = randCol()
                    randRow = randRow()
                }
            }
        }
    }
    fun createPuzzle(){
        buildPuzzle()
        /* Adds in the missing spots */
        for (row in puzzleBoard.indices){
            for (col in puzzleBoard.indices) {
                if (puzzleBoard[row][col] == ".") {
                    puzzleBoard[row][col] = charArray.random()

                }
            }
        }
    }
    fun randRow() : Int {
        return (0..(row - 1)).random()
    }
    fun randCol() : Int {
        return (0..(col - 1)).random()
    }
    fun randDirection() : String {
        return directionArray[(0..7).random()]
    }
}

 fun main() {
     val start = System.currentTimeMillis()
     for (i in 1..10) {
         var puzzle = Puzzle(30,30,"src/main/kotlin/wordsearch/words.txt")
         puzzle.createPuzzle()
     }
     var totalTime = System.currentTimeMillis() - start
     println("Total Time to create 10 puzzles: $totalTime milli-seconds")
     println("Average Time to create 1 puzzles: ${totalTime/10} milli-seconds")

     /*

                *** What I did for a faster run time ***

                * Remove all aspects of printing/returning a string. This included adding spaces in between puzzle pieces.
                * Removed unused functions
                * Created my character array as an inner for loop inside the word array instead of doing that step later.
                * Adjusted my randDirection function. Instead of returning a random number from the indices of the array, I hard coded a range of 0-7. This saved me almost 10-20 milliseconds in total time.

    */
 }



