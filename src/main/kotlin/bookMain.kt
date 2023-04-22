import java.awt.print.Book
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         4/20/23
 *  Assignment:   Book Class
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/

var bookFile = "src/main/kotlin/books.txt"
class BookLibrary(val title : String , val author: String, val publishYear: Int, val numPages: Int, val isbn: String) {
        override fun toString(): String {
                return "${title}\t${author}\t${publishYear}\t${numPages}\t${isbn}"
        }
}

var userChoice = 0

fun main(){
//
var libraryList = mutableListOf<BookLibrary>()
var lines = File(bookFile).readLines()
for (i in lines) {
        var crumbs = i.split("\t")
        var innerList = BookLibrary(crumbs[0], crumbs[1], crumbs[2].toInt(), crumbs[3].toInt(), crumbs[4])
        libraryList.add(innerList)
}

//print("Make a selection: ")
//userChoice = readln().toInt()
//while (userChoice != 10 ) {
//        when(userChoice) {
//        1 -> printAllBooks(libraryList)
//        }
//}
printAllBooks(libraryList)

}

fun header() {
println("Geeks Publishing, Inc.\n" + "Name".padEnd(30) + " " + "Author".padEnd(15) + " " + "Pub Yr".padEnd(6) + " " + "Pages".padEnd(5) + " " + "ISBN".padEnd(13) + " " + "URL".padEnd(7) + "\n" + "-".repeat(30) + " " + "-".repeat(15) + " " + "-".repeat(6) + " " + "-".repeat(5) + " " + "-".repeat(13) + " " + "-".repeat(7))
}

fun printAllBooks (books: MutableList<BookLibrary>) {
        header()
        for (i in books) {
                if (i.title.length <= 30) {
                        println(i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + "Pub Yr".padEnd(6) + " " + "Pages".padEnd(5) + " " + "ISBN".padEnd(13) + " " + "URL".padEnd(7))
                }
                else {
                        println(i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + "Pub Yr".padEnd(6) + " " + "Pages".padEnd(5) + " " + "ISBN".padEnd(13) + " " + "URL".padEnd(7))
                }
        }
}

fun bookMenu() {

}



/*

1.      View all Books
2.      Add book
3.      Update book
4.      Delete book
5.      View book with most pages
6.      View book with least pages
7.      View books with pages greater than or equal to 200
8.      View books with pages less than 200
9.      View books with pages between 50-300 inclusive
10.     Exit   (be sure to save file back out)

*/
//      10.     val writeFile = File( "src/main/kotlin/bookTEST.txt").printWriter()
//for (i in libraryList) {
//        writeFile.println(i)
//}
//        writeFile.close()

/*
* COMPLETED
----------------
* 1,10,
* Header, Write and Save, Truncated print
*
*
*
* */



// Key will most likely be ISBN

// will most likely use trim to fit the Title in the menu


//val writeFile = File( fileName ).printWriter();
//writeFile.println(newLine)
//writeFile.close()

