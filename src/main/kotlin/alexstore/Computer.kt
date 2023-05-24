package alexstore

open class Computer(category: String, name : String, retail: Double, cost : Double, inStock: Int, sold: Int, description: String, var ram : Int, var hddCapacity: Int, var cpuSpeed : String) : Part(category, name, retail, cost, inStock, sold, description) {



    override fun toString():String {
        return super.toString() + ", $ram, $hddCapacity, $cpuSpeed"
    }

    override fun toTab() : String {
            return super.toTab() + "\t${ram}\t${hddCapacity}\t${cpuSpeed}"
    }

    override fun toTableString(): String {
        return super.toTableString() + "${ram.toString().padEnd(colEight)}${hddCapacity.toString().padEnd(colNine)}${cpuSpeed.padEnd(colTen)}\n"
    }

    override fun toDetailedReport(): String {
        return super.toDetailedReport() + """
            |
            |${"GB's of Ram:".padEnd(22)}${ram}
            |${"HardDrive Capacity:".padEnd(22)}${hddCapacity}
            |${"Processor Speed:".padEnd(22)}${cpuSpeed}
            """.trimMargin()
    }


}



/*
open class Part(var name : String , var retail: Int, var cost : Int, var inStock: Int, var sold : Int, var description : String, var category : String){
    override fun toString(): String {
        return """
        |${"Part Category:".padEnd(20)}${category}
        |${"Part Name:".padEnd(20)}${name}
        |${"Retail Price:".padEnd(20)}${retail}
        |${"Cost:".padEnd(20)}${cost}
        |${"QTY In Stock:".padEnd(20)}${inStock}
        """.trimMargin()
    }
}
 */