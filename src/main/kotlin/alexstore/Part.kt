package alexstore
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         5/20/23
 *  Assignment:
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/
open class Part(var category: String,  var name : String , var retail: Double, var cost : Double, var inStock: Int, var sold : Int, var description : String){

    val myFile  = File(fileName).readLines()
    val colList = myFile[0].split("\t").toMutableList()
    open val colOne = colList[0].toInt()
    open val colTwo = colList[1].toInt()
    open val colThree = colList[2].toInt()
    open val colFour = colList[3].toInt()
    open val colFive = colList[4].toInt()
    open val colSix = colList[5].toInt()
    open val colSeven = colList[6].toInt()
    open val colEight = colList[7].toInt()
    open val colNine = colList[8].toInt()
    open val colTen = colList[9].toInt()

    override fun toString(): String {
        return "$category, $name, $retail, $cost, $inStock, $sold, $description"
    }

    open fun toTab() : String {
        return "${category}\t${name}\t${retail.toInt()}\t${cost.toInt()}\t${inStock}\t${sold}\t${description}"
    }

    open fun toTableString() : String {
        var formattedRetail = "$" + "%.2f".format(retail)
        var formattedCost = "$" + "%.2f".format(cost)
        var shortDescr = ""
        if (description.length >= 38) {
        shortDescr = description.slice(0..37)
        }
        else{
        shortDescr = description
        }
        return "${category.padEnd(colOne)}${name.padEnd(colTwo)}${formattedRetail.padEnd(colThree)}${formattedCost.padEnd(colFour)}${inStock.toString().padEnd(colFive)}${sold.toString().padEnd(colSix)}${shortDescr.padEnd(colSeven)}"

    }

    open fun totalsReportString() : String {
        val profit = retail - cost
        val grandTotal = profit * sold
        var formattedRetail = "$" + "%.2f".format(retail)
        var formattedCost = "$" + "%.2f".format(cost)
        var formattedProfit = "$" + "%.2f".format(profit)
        var formattedTotal = "$" + "%.2f".format(grandTotal)
        return "${category.padEnd(colOne)}${name.padEnd(colTwo)}${formattedRetail.padEnd(colThree)}${formattedCost.padEnd(colFour)}${inStock.toString().padEnd(colFive)}${sold.toString().padEnd(colSix)}${formattedProfit.padStart(colOne)}${formattedTotal.padStart(colOne)}\n"

    }

    open fun toDetailedReport() : String {
        return """
            |${"Part Category:".padEnd(22)}$category
            |${"Part Name:".padEnd(22)}$name
            |${"Retail Price:".padEnd(22)}$${"%.2f".format(retail)}
            |${"Cost:".padEnd(22)}$${"%.2f".format(cost)}
            |${"QTY In Stock:".padEnd(22)}$inStock
            |${"QTY Sold:".padEnd(22)}$sold
            |${"Detailed Description:".padEnd(22)}$description
            """.trimMargin()
    }


}