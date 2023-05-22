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


fun main() {

    val storeInventory = Inventory(fileName)
//    println(storeInventory.toTableString())
    storeInventory.detailedReport(5)
}