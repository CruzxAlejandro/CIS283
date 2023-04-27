import java.awt.print.Book
import java.io.File
import java.io.PipedReader

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         4/20/23
 *  Assignment:   Book Class
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/

var bookFile = "src/main/kotlin/books.txt"
class BookLibrary(var title : String , var author: String, var publishYear: Int, var numPages: Int, var isbn: String) {
        override fun toString(): String {

                return ("${title}\t${author}\t${publishYear}\t${numPages}\t${isbn}")
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
        3 -> updateBook(libraryList)
        4 -> removeBook(libraryList)
        5 -> mostPages(libraryList)
        6 -> leastPages(libraryList)
        7 -> pagesGreaterThan(libraryList)
        8 -> pagesLessThan(libraryList, 0..200)
        9 -> pagesBetween(libraryList, 50..300)
        10 -> println("Thanks! Saving books and closing program.")
        else -> println("Please submit a valid option. (1-10)")
        }
}
val writeFile = File(bookFile).printWriter()
//for (i in libraryList) {
//        writeFile.println(i)
//}

for(i in libraryList){
        writeFile.println(i)
}


writeFile.close()

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
fun header() {
println("Geeks Publishing, Inc.\n".padStart(63) + "Name".padEnd(30) + " " + "Author".padEnd(15) + " " + "Pub Yr".padEnd(6) + " " + "Pages".padEnd(5) + " " + "ISBN".padEnd(13) + " " + "URL".padEnd(7) + "\n" + "-".repeat(30) + " " + "-".repeat(15) + " " + "-".repeat(6) + " " + "-".repeat(5) + " " + "-".repeat(13) + " " + "-".repeat(7))
}
fun headerIndent(){
        println(" ".repeat(5) + "Geeks Publishing, Inc.\n".padStart(63) + " ".repeat(5) + "Name".padEnd(30) + " " + "Author".padEnd(15) + " " + "Pub Yr".padEnd(6) + " " + "Pages".padEnd(5) + " " + "ISBN".padEnd(13) + " " + "URL".padEnd(7) + "\n" + " ".repeat(5) +"-".repeat(30) + " " + "-".repeat(15) + " " + "-".repeat(6) + " " + "-".repeat(5) + " " + "-".repeat(13) + " " + "-".repeat(7))
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
fun updateBook(books: MutableList<BookLibrary>){
        headerIndent()
        var bullets = 1
        for (i in books) {
                if (i.title.length <= 30) {
                        println("${bullets}. ".padStart(5) + i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                }
                else {
                        println("${bullets}. ".padStart(5) + i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                }
                bullets++
        }
        println()
        print("Select the book you would like to update: ")
        var updateThis = readln().toInt() - 1
        var oldName = books[updateThis].title

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
        books[updateThis] = BookLibrary(title, author, pubYear, pages, isbn)
        println()
        println("\"${oldName}\" has been updated to \"${title}\"")
}
fun removeBook(books: MutableList<BookLibrary>) {
        headerIndent()
        var bullets = 1
        for (i in books) {
                if (i.title.length <= 30) {
                        println("${bullets}. ".padStart(5) + i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                }
                else {
                        println("${bullets}. ".padStart(5) + i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                }
                bullets++
        }
        println()
        print("Select the book you would like to delete: ")
        var deleteThis = readln().toInt() - 1
        println(books[deleteThis].title + " has been deleted from the library.")
        books.removeAt(deleteThis)
}
fun mostPages(books: MutableList<BookLibrary>){
        var pagesMax = mutableListOf<Int>()
        for (i in books){
                pagesMax.add(i.numPages)
        }

        var mostPages = pagesMax.indexOf(pagesMax.maxOrNull())
        println()
        header()
        if (books[mostPages].title.length <= 30) {
                println(books[mostPages].title.padEnd(30) + " " + books[mostPages].author.padEnd(15) + " " + books[mostPages].publishYear.toString().padStart(6) + " " + books[mostPages].numPages.toString().padStart(5) + " " + books[mostPages].isbn.padEnd(13) + " " + "https://www.biblio.com/${books[mostPages].isbn}")
        }
        else {
                println(books[mostPages].title.slice(0..29).padEnd(30) + " " + books[mostPages].author.padEnd(15) + " " + books[mostPages].publishYear.toString().padStart(6) + " " + books[mostPages].numPages.toString().padStart(5) + " " + books[mostPages].isbn.padEnd(13) + " " + "https://www.biblio.com/${books[mostPages].isbn}")
        }
}
fun leastPages(books: MutableList<BookLibrary>){
        var pagesMin = mutableListOf<Int>()
        for (i in books){
                pagesMin.add(i.numPages)
        }
        var leastPages = pagesMin.indexOf(pagesMin.minOrNull())

        println()
        header()
        if (books[leastPages].title.length <= 30) {
                println(books[leastPages].title.padEnd(30) + " " + books[leastPages].author.padEnd(15) + " " + books[leastPages].publishYear.toString().padStart(6) + " " + books[leastPages].numPages.toString().padStart(5) + " " + books[leastPages].isbn.padEnd(13) + " " + "https://www.biblio.com/${books[leastPages].isbn}")
        }
        else {
                println(books[leastPages].title.slice(0..29).padEnd(30) + " " + books[leastPages].author.padEnd(15) + " " + books[leastPages].publishYear.toString().padStart(6) + " " + books[leastPages].numPages.toString().padStart(5) + " " + books[leastPages].isbn.padEnd(13) + " " + "https://www.biblio.com/${books[leastPages].isbn}")
        }
}
fun pagesGreaterThan(books: MutableList<BookLibrary>){
        header()
        for(i in books){
                if (i.numPages >= 200){
                        if (i.title.length <= 30 ){
                                println(i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                        }
                        else {
                                println(i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                        }
                }
        }
}
fun pagesLessThan(books: MutableList<BookLibrary>, pagesWithin: IntRange) {
        header()
        for(i in books){
                if (i.numPages in pagesWithin){
                        if (i.title.length <= 30 ){
                                println(i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                        }
                        else {
                                println(i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                        }
                }
        }
}
fun pagesBetween(books: MutableList<BookLibrary>, betweenThis: IntRange){
        header()
        for(i in books){
                if (i.numPages in betweenThis){
                        if (i.title.length <= 30 ){
                                println(i.title.padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                        }
                        else {
                                println(i.title.slice(0..29).padEnd(30) + " " + i.author.padEnd(15) + " " + i.publishYear.toString().padStart(6) + " " + i.numPages.toString().padStart(5) + " " + i.isbn.padEnd(13) + " " + "https://www.biblio.com/${i.isbn}")
                        }
                }
        }
}