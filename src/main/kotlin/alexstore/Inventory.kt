package alexstore
import java.io.File
import alexstore.Part

open class Inventory(fileName : String) {
    var columnLength = mutableListOf<String>()
    var header = mutableListOf<String>()
    var fullInventoryList = mutableListOf<Part>()
    var writeFile = File("/Users/cruzlopez/IdeaProjects/CIS283/src/main/kotlin/alexstore/testingFile.tsv").printWriter()

    init {
        var lines = File(fileName).readLines()
        var pattern = "\t"
        columnLength = lines[0].split(pattern).toMutableList()
        header = lines[1].replace("_"," ").split(pattern).toMutableList()

        for (i in 2..lines.lastIndex) {
            var fields = lines[i].split(pattern).toMutableList()
            fields[0] = fields[0].uppercase()
            if (fields[0] == "COMPUTER") {
                var part = Computer(fields[0].trim(), fields[1].trim(), fields[2].toDouble(), fields[3].toDouble(), fields[4].toInt(), fields[5].toInt(), fields[6].trim(), fields[7].toInt(), fields[8].toInt(), fields[9].trim())
                fullInventoryList.add(part)
            }
            else if(fields[0] == "PRINTER") {
                var part = Printer(fields[0].trim(), fields[1].trim(), fields[2].toDouble(), fields[3].toDouble(), fields[4].toInt(), fields[5].toInt(), fields[6].trim(), fields[7].toBoolean(), fields[8].toInt(), fields[9].toBoolean())
                fullInventoryList.add(part)
            }
            else if(fields[0] == "TABLET") {
                var part = Tablet(fields[0].trim(), fields[1].trim(), fields[2].toDouble(), fields[3].toDouble(), fields[4].toInt(), fields[5].toInt(), fields[6].trim(), fields[7].trim(), fields[8].toInt(), fields[9].toBoolean())
                fullInventoryList.add(part)
            }
        }
    }
    val colOne = columnLength[0].toInt()
    val colTwo = columnLength[1].toInt()
    val colThree = columnLength[2].toInt()
    val colFour = columnLength[3].toInt()
    val colFive = columnLength[4].toInt()
    val colSix = columnLength[5].toInt()
    val colSeven = columnLength[6].toInt()
    val colEight = columnLength[7].toInt()
    val colNine = columnLength[8].toInt()
    val colTen = columnLength[9].toInt()

    fun indexString() : String {
        var retString = ""
        retString += toHeaderString()
        for ((index, i) in fullInventoryList.withIndex() ) {
            retString += "${index + 1}. ${i.toTableString()}"
        }
        return retString
    }

    fun toTableString() : String {
        var reString = ""
        for (i in fullInventoryList) {
            reString += i.toTableString()
        }
    return reString
    }

    fun toHeaderString() : String {
        var retString = ""
        retString += " ".repeat(75) + "Parts Inventory\n"
        retString += "${header[0].toString().replace("Part","Alexs").padEnd(colOne)}${header[1].toString().padEnd(colTwo)}${header[2].toString().padEnd(colThree)}${header[3].toString().padEnd(colFour)}${header[4].toString().padEnd(colFive)}${header[5].toString().padEnd(colSix)}${header[6].toString().padEnd(colSeven)}${header[7].toString().padEnd(colEight)}${header[8].toString().padEnd(colNine)}${header[9].toString().padEnd(colTen)}\n"
        retString += "${"-".repeat(colOne - 1)} ${"-".repeat(colTwo - 1)} ${"-".repeat(colThree - 1)} ${"-".repeat(colFour - 1)} ${"-".repeat(colFive - 1)} ${"-".repeat(colSix - 1)} ${"-".repeat(colSeven - 1)} ${"-".repeat(colEight - 1)} ${"-".repeat(colNine - 1)} ${"-".repeat(colTen - 1)}\n"
        return retString
    }

    fun toTableHeader() : String {
        var whiteSpace = """\s""".toRegex()
        header[0] = header[0].replace(whiteSpace, "_")
        header[2] = header[2].replace(whiteSpace, "_")
        header[4] = header[4].replace(whiteSpace, "_")
        header[5] = header[5].replace(whiteSpace, "_")
        header[6] = header[6].replace(whiteSpace, "_")

        var retString = ""
        var num = 0
        for (i in columnLength) {
                if (num < columnLength.size - 1) {
                    retString += "${i}\t"
                    num++
                }
                if (num == columnLength.size - 1) {
                    retString += "${i}\n"
                    num++
                }

        }
        num = 0
        for (i in header) {
                    if (num < header.size - 1) {
                        retString += "${i}\t"
                        num++
                    }
                    if (num == header.size - 1) {
                        retString += "${i}\n"
                        num++
                    }

        }
        return retString
    }

    fun saveToDisk() {
        writeFile.print(toTableHeader())
        for (i in fullInventoryList) {
            writeFile.print(i.toTab())
        }
        writeFile.close()
    }

    fun specificCategory(choice : String) : String {
        var retString = ""
        for (i in fullInventoryList) {
            if (i.category == choice.uppercase()) {
                retString += i.toTableString()
            }
        }
        return retString
    }

    fun detailedReport(choice : Int) : String {
        var retString = ""

        retString += "-".repeat(50) + "\n"
        retString += fullInventoryList[choice].toDetailedReport() + "\n"
        retString += "-".repeat(50)
        return retString
    }

    fun addPart(category: String, name : String , retail : Double, cost : Double, inStock: Int, sold : Int, description : String, xxx : String, yyy: String, zzz:String) {
        if (category == "COMPUTER") {
            fullInventoryList.add(
                Computer(
                    category,
                    name,
                    retail,
                    cost,
                    inStock,
                    sold,
                    description,
                    xxx.toInt(),
                    yyy.toInt(),
                    zzz
                )
            )
        }
        else if(category == "PRINTER")
        {
            fullInventoryList.add(
                Printer(
                    category,
                    name,
                    retail,
                    cost,
                    inStock,
                    sold,
                    description,
                    xxx.toBoolean(),
                    yyy.toInt(),
                    zzz.toBoolean()
                )
            )
        }
        else if (category == "TABLET") {
            fullInventoryList.add(
                Tablet(
                    category,
                    name,
                    retail,
                    cost,
                    inStock,
                    sold,
                    description,
                    xxx,
                    yyy.toInt(),
                    zzz.toBoolean()
                )
            )
        }
    }

}

