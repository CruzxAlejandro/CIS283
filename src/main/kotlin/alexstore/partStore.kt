package alexstore
import alexstore.Inventory
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         5/20/23
 *  Assignment:   Part store
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/

const val fileName = "/Users/cruzlopez/IdeaProjects/CIS283/src/main/kotlin/alexstore/parts_database.tsv"

fun promptForInt(message:String, range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE) : Int{
    var isInt = """\A-?\d+\Z""".toRegex()
    var firstTime = true
    var input: String? = ""
    do {
        if(!firstTime) {
            println("""|${" "}
                               |ERROR
                               |${"-".repeat(50)}
                               |Invalid Number - Please enter a whole number between ${range.first} and ${range.last}
                               |${"-".repeat(50)}
                               |${" "}
                                """.trimMargin())
        }
        print("\n" + message)
        input = readLine()
        firstTime = false
    } while (input.isNullOrEmpty() || !isInt.matches(input) || input.toInt() !in range)
    return input.toInt()
}
fun promptForString(message: String) : String {
    var input : String? = ""
    var firstTime = true
    do {
        if (!firstTime) {
            println("""|${" "}
                               |ERROR
                               |${"-".repeat(50)}
                               |Please enter a valid file name.
                               |${"-".repeat(50)}
                               |${" "}
                                """.trimMargin())
        }
        print(message)
        input = readLine()
        firstTime = false
    }while (input.isNullOrEmpty())
    return input
}
class Menu(var menuItems: Array<String>, var prompt : String) {
    var quit = menuItems.size
    fun displayMenu() : Int {
        for ( (index, item) in menuItems.withIndex()) {
            println("${(index + 1)}. ${item}")
        }
        return promptForInt(prompt, 1..menuItems.size)
    }
}
/*
Ask Dave about how I have my Add part setup
Build the rest of Add part.
 */

fun main() {

    val storeInventory = Inventory(fileName)
    val menu = Menu(arrayOf(
            "List All Parts",
            "Show All of a particular Category",
            "Sell a Part",
            "Increase inventory of a particular part",
            "Update elements of a single part",
            "Add a brand new part to inventory",
            "Completely remove a part from inventory",
            "Show the detail information about a part",
            "Show Total Inventory with totals of Cost, Retail Price, Quantity Sold, Profit",
            "QUIT"
        ),"Please select an option: ")
    do {
        var choice = menu.displayMenu()
        when(choice) {
            1 -> listAllParts(storeInventory)
            2 -> showAllCategory(storeInventory)
            6 -> addPartInventory(storeInventory)
            8 -> showDetailedInfo(storeInventory)
        }
    }while (choice != menu.quit)
}

fun listAllParts(storeInventory: Inventory){
    println()
    print(storeInventory.toHeaderString())
    print( storeInventory.toTableString())
    println()
}
fun showAllCategory(storeInventory: Inventory){
    print("Select a category to filter part inventory: ")
    val category = readln()
    println()
    print(storeInventory.toHeaderString())
    println(storeInventory.specificCategory(category))
}
fun showDetailedInfo(storeInventory: Inventory) {
    println()
    println(storeInventory.indexString())
    println()
    print("Select a part to view: ")
    val choice = readln().toInt() - 1
    println(storeInventory.detailedReport(choice))
    println()
}
fun addPartInventory(storeInventory: Inventory) {
    print("What category of part would you like to add (COMPUTER, PRINTER, TABLET): ")
    var choice = readln().uppercase()
    if (choice == "COMPUTER") {
        var category = choice
        print("Enter name of the part: ")
        var name = readln()
        print("Enter the retail price: ")
        var retail = readln().toDouble()
        print("Enter the cost: ")
        var cost = readln().toDouble()
        print("Enter the QTY in stock: ")
        var stock = readln().toInt()
        print("Enter the QTY sold: ")
        var sold = readln().toInt()
        print("Enter a detailed description: ")
        var desc = readln()
        print("Enter the GB's of Ram: ")
        var ram = readln()
        print("Enter the hard drive capacity: ")
        var hddCapacity = readln()
        print("Enter the processor speed ")
        var cpuSpeed = readln()
        storeInventory.addPart(category, name, retail, cost, stock, sold, desc, ram, hddCapacity, cpuSpeed)
        println()
        println(storeInventory.toHeaderString())
        println(storeInventory.toTableString())
    }
    else if(choice == "PRINTER"){
        var category = choice
        print("Enter name of the part: ")
        var name = readln()
        print("Enter the retail price: ")
        var retail = readln().toDouble()
        print("Enter the cost: ")
        var cost = readln().toDouble()
        print("Enter the QTY in stock: ")
        var stock = readln().toInt()
        print("Enter the QTY sold: ")
        var sold = readln().toInt()
        print("Enter a detailed description: ")
        var desc = readln()
        print("Does the printer have color? (true/false): ")
        var color = readln().lowercase()
        print("Enter the pages per minute: ")
        var pages = readln()
        print("Does the printer have a scanner? (true/false): ")
        var scanner = readln().lowercase()

        storeInventory.addPart(category, name, retail, cost, stock, sold, desc, color, pages, scanner)
        println()
        println(storeInventory.toHeaderString())
        println(storeInventory.toTableString())
    }
    else if(choice == "TABLET") {
        var category = choice
        print("Enter name of the part: ")
        var name = readln()
        print("Enter the retail price: ")
        var retail = readln().toDouble()
        print("Enter the cost: ")
        var cost = readln().toDouble()
        print("Enter the QTY in stock: ")
        var stock = readln().toInt()
        print("Enter the QTY sold: ")
        var sold = readln().toInt()
        print("Enter a detailed description: ")
        var desc = readln()
        print("Enter the screen size: ")
        var screenSize = readln()
        print("Enter the MBs of ram: ")
        var ram = readln()
        print("Does the tablet have an SD slot? (true/false): ")
        var sdSlot = readln().lowercase()

        storeInventory.addPart(category, name, retail, cost, stock, sold, desc, screenSize, ram, sdSlot)
        println()
        println(storeInventory.toHeaderString())
        println(storeInventory.toTableString())
        println()
    }

}


/*
Formatted Code Correctly
Menu with 10 options (includes quit)
Parts Class with 7 properties
Computer Class with 3 properties - child of Parts
Printer Class with 3 properties - child of Parts
Tablet Class with 3 properties - child of Parts
All files share the same package name
Inventory Class
Reads from TSV file and creates correct part objects
Writes all parts and header back to the same TSV file.
List all parts
toString AND toTab in all classes
Sell a part changes quantities sold/inventory correctly
Add a new part
Remove a part
Show detail info for a part
Show total inventory with 4 separate totals (Cost, Retail Price, Qty Sold, Profit)
"super" method is used appropriately to maximize code reuse.
Update a Part
 */