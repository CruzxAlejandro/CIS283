package alexstore

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         5/20/23
 *  Assignment:
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/
open class Printer (category: String, name : String, retail: Double, cost : Double, inStock: Int, sold: Int, description: String, var color: Boolean, var pagesPerMin: Int, var scanner: Boolean) : Part(category, name, retail, cost, inStock, sold, description) {


    override fun toString():String {
        return super.toString() + ", $color, $pagesPerMin, $scanner"

    }

    override fun toTab() : String {
        return super.toTab() + "\t${color}\t${pagesPerMin}\t${scanner}\n"
    }

    override fun toTableString(): String {
        return super.toTableString() + "${color.toString().padEnd(colEight)}${pagesPerMin.toString().padEnd(colNine)}${scanner.toString().padEnd(colTen)}\n"
    }

    override fun toDetailedReport(): String {
        return super.toDetailedReport() + """
            |
            |${"Color:".padEnd(22)}${color}
            |${"Pages Per Minute:".padEnd(22)}${pagesPerMin}
            |${"Scanner:".padEnd(22)}${scanner}
            """.trimMargin()
    }
}

