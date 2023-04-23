import regexsearch.fileName
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

var userChoice = 0

while (userChoice != 10 ) {
        userChoice = bookMenu()
        when(userChoice) {
        1 -> printAllBooks(libraryList)
        2 -> addBook(libraryList)
        10 -> println("Thanks! Saving books and closing program.")
        else -> println("Please submit a valid option. (1-10)")

        }
}
val writeFile = File(bookFile).printWriter()
for (i in libraryList) {
        writeFile.print(i)
}
writeFile.close()


//printAllBooks(libraryList)

//libraryList.removeAt(2)
//printAllBooks(libraryList)

}

fun header() {
println("Geeks Publishing, Inc.\n".padStart(63) + "Name".padEnd(30) + " " + "Author".padEnd(15) + " " + "Pub Yr".padEnd(6) + " " + "Pages".padEnd(5) + " " + "ISBN".padEnd(13) + " " + "URL".padEnd(7) + "\n" + "-".repeat(30) + " " + "-".repeat(15) + " " + "-".repeat(6) + " " + "-".repeat(5) + " " + "-".repeat(13) + " " + "-".repeat(7))
}

fun printAllBooks (books: MutableList<BookLibrary>) {
        header()
        for (i in books) {
                if (i.title.length <= 30) {
                        println(i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                }
                else {
                        println(i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                }
        }
}

fun addBook(books: MutableList<BookLibrary>) {
        printAllBooks(books)
        println()
        print("Please enter the Name of the new book: ")
        var title = readln().toString()
        while (title.isEmpty()) {
                print("Please enter the Name of the new book: ")
                title = readln().toString()
        }
        print("Please enter the Author of ${title}: ")
        var author = readln().toString()
        while (author.isEmpty()) {
                print("Please enter the Author of ${title}: ")
                author = readln().toString()
        }
        print("Please enter the Publication Year: ")
        var pubYear = readln().toInt()
        while (pubYear <= 1600 ) {
                print("Please enter the Publication Year: ")
                pubYear = readln().toInt()
        }
        print("Please enter the number of pages: ")
        var pages = readln().toInt()
        while (pages <= 0 ) {
                print("Please enter the number of pages: ")
                pages = readln().toInt()
        }
        print("Please enter the ISBN: ")
        var isbn = readln()
        while (isbn.isEmpty()) {
                print("Please enter the ISBN: ")
                isbn = readln()
        }

        var newBook = BookLibrary(title, author, pubYear, pages, isbn)
        books.add(newBook)

        println("\nNew book \"${title}\" has been added!")
}
fun bookMenu() : Int {
        println()
        println("1.".padStart(3) + " " + "View all Books")
        println("2.".padStart(3) + " " + "Add Book")
        println("3.".padStart(3) + " " + "Update Book")
        println("4.".padStart(3) + " " + "Delete Book")
        println("5.".padStart(3) + " " + "View book with most pages")
        println("6.".padStart(3) + " " + "View book with least pages")
        println("7.".padStart(3) + " " + "View book pages greater than or equal to 200")
        println("8.".padStart(3) + " " + "View books with pages less than 200")
        println("9.".padStart(3) + " " + "View books with pages between 50-300 inclusive")
        println("10.".padStart(3) + " " + "Exit")
        println()
        print("Please enter your selection: ")
        return readln().toInt()

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
* 1,2, 10,
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





