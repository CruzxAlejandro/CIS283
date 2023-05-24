package alexstore

open class Tablet(category: String, name : String, retail: Double, cost : Double, inStock: Int, sold: Int, description: String, var screenSize : String, var ramMB: Int, var slotSD : Boolean) : Part(category, name, retail, cost, inStock, sold, description) {

    override fun toString():String {
        return super.toString() + ", $screenSize, $ramMB, $slotSD"
    }

    override fun toTab() : String {
        return super.toTab() + "\t${screenSize}\t${ramMB}\t${slotSD}"
    }

    override fun toTableString(): String {
        return super.toTableString() + "${screenSize.padEnd(colEight)}${ramMB.toString().padEnd(colNine)}${slotSD.toString().padEnd(colTen)}\n"
    }

    override fun toDetailedReport(): String {
        return super.toDetailedReport() + """
            |
            |${"Screen size:".padEnd(22)}${screenSize}
            |${"MB's of RAM:".padEnd(22)}${ramMB}
            |${"SD slot:".padEnd(22)}${slotSD}
            """.trimMargin()
    }
}