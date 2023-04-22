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
                return "${title}\t${author}\t${publishYear}\t${numPages}\t${isbn} "
        }
}


//val libraryMap: MutableMap<String, BookLibrary> = mutableMapOf()

fun main(){
//
var libraryList = mutableListOf<BookLibrary>()
var lines = File(bookFile).readLines()
for (i in lines) {
        var crumbs = i.split("\t")
        var innerList = BookLibrary(crumbs[0], crumbs[1], crumbs[2].toInt(), crumbs[3].toInt(), crumbs[4])
        libraryList.add(innerList)
}


println(libraryList[1])
println(libraryList[2])
}


// Key will most likely be ISBN
// Loop through lines,
//


// will most likely use trim to fit the Title in the menu


//val writeFile = File( fileName ).printWriter();
//writeFile.println(newLine)
//writeFile.close()

