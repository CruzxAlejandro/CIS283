package weatherdbalexc
import java.io.File
/************************************************************
 *  Name:         Alex Cruz
 *  Date:         2/20/23
 *  Assignment:   Hashmaps and Database Reporting
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/

const val weatherDBFile = "src/main/kotlin/weatherdbalexc/weather.db"
data class Weather (val month : Int, val day : Int, val sunrise : String, val sunset : String, val mean : Int, val avgHi : Int, val avgLo : Int, val recHi : Int, val recHiYr : Int, val recLo : Int, val recLoYr : Int, val avgPrecip : Double)

fun main(){

    val weatherDB: MutableMap<String, Weather> = mutableMapOf()
    val firstLineArray = mutableListOf<String>()


    val lines = File(weatherDBFile).readLines()
    var firstLine = true

    for (line in lines) {
        if (firstLine) {
            val bits = line.split(",")
            firstLineArray += bits
            firstLine = false
        }
        else {
            val crumbs = line.split(",")
            val weatherRecord = Weather( crumbs[0].toInt(), crumbs[1].toInt(), crumbs[2], crumbs[3], crumbs[4].toInt(), crumbs[5].toInt(), crumbs[6].toInt(), crumbs[7].toInt(), crumbs[8].toInt(), crumbs[9].toInt(), crumbs[10].toInt(), crumbs[11].toDouble())
            weatherDB["${crumbs[0]} / ${crumbs[1]}"] = weatherRecord
        }
    }

    var userChoice = 0
    while(userChoice != 8) {
        userChoice = menu()
        if (userChoice == 1) {
            println(" ".repeat(61) + "All records in Spokane" + " ".repeat(61))
            println("")
            printHeader(firstLineArray)
            printAllRecords(weatherDB)
            println("")
        }
        else if (userChoice == 2) {
            print("Please enter a month (1-12) to print: ")
            val userMonth = readln().toInt()
            var printSpecificMonth = ""

            when(userMonth){
                1 -> printSpecificMonth = "January"
                2 -> printSpecificMonth = "February"
                3 -> printSpecificMonth = "March"
                4 -> printSpecificMonth = "April"
                5 -> printSpecificMonth = "May"
                6 -> printSpecificMonth = "June"
                7 -> printSpecificMonth = "July"
                8 -> printSpecificMonth = "August"
                9 -> printSpecificMonth = "September"
                10 -> printSpecificMonth = "October"
                11 -> printSpecificMonth = "November"
                12 -> printSpecificMonth = "December"
                else -> println("Error: Please enter a valid month (1-12)")
            }
            println(" ".repeat(50) + "All records in Spokane for the month of ${printSpecificMonth}" + " ".repeat(50))
            println("")
            printHeader(firstLineArray)
            printOneMonthRecord(weatherDB, userMonth)
            println("")
        }
        else if (userChoice == 3) {
            println(" ".repeat(50) + "Highest recorded temperature in Spokane" + " ".repeat(50))
            println("")
            printHeader(firstLineArray)
            printHighestRecordTemp(weatherDB)
            println("")
        }
        else if (userChoice == 4) {
            println(" ".repeat(50) + "Lowest recorded temperature in Spokane" + " ".repeat(50))
            println("")
            printHeader(firstLineArray)
            printLowestRecordTemp(weatherDB)
            println("")
        }
        else if (userChoice == 5){
            print("Please enter a month (1-12) to print: ")
            val userMonth = readln().toInt()
            var printSpecificMonth = ""
            when(userMonth){
                1 -> printSpecificMonth = "January"
                2 -> printSpecificMonth = "February"
                3 -> printSpecificMonth = "March"
                4 -> printSpecificMonth = "April"
                5 -> printSpecificMonth = "May"
                6 -> printSpecificMonth = "June"
                7 -> printSpecificMonth = "July"
                8 -> printSpecificMonth = "August"
                9 -> printSpecificMonth = "September"
                10 -> printSpecificMonth = "October"
                11 -> printSpecificMonth = "November"
                12 -> printSpecificMonth = "December"
                else -> println("Error: Please enter a valid month (1-12)")
            }

            println(" ".repeat(50) + "Highest recorded temperature in Spokane for the month of ${printSpecificMonth}" + " ".repeat(50))
            println("")
            printHeader(firstLineArray)
            printRecordHighOneMonth(weatherDB, userMonth)
            println("")
        }
        else if (userChoice == 6){
            print("Please enter a month (1-12) to print: ")
            val userMonth = readln().toInt()
            var printSpecificMonth = ""

            when(userMonth){
                1 -> printSpecificMonth = "January"
                2 -> printSpecificMonth = "February"
                3 -> printSpecificMonth = "March"
                4 -> printSpecificMonth = "April"
                5 -> printSpecificMonth = "May"
                6 -> printSpecificMonth = "June"
                7 -> printSpecificMonth = "July"
                8 -> printSpecificMonth = "August"
                9 -> printSpecificMonth = "September"
                10 -> printSpecificMonth = "October"
                11 -> printSpecificMonth = "November"
                12 -> printSpecificMonth = "December"
                else -> println("Error: Please enter a valid month (1-12)")
            }
            println(" ".repeat(50) + "Lowest recorded temperature in Spokane for the month of ${printSpecificMonth}" + " ".repeat(50))
            println("")
            printHeader(firstLineArray)
            printRecordLowOneMonth(weatherDB, userMonth)
        }
        else if (userChoice == 7){
            print("Please enter a month (1-12) to print: ")
            val userMonth = readln().toInt()
            println("")
            printAveragePrecip(weatherDB, userMonth)
            println("")
        }
        else if (userChoice == 8) {
            println("Quitting program")
        }
        else {
            println("Please enter a valid option (1-7)")
            println("")
        }
    }
}

fun menu() : Int {
    println("1. Print ALL Records")
    println("2. Print Only Records from a single month")
    println("3. Print Record with Highest Recorded Temperature")
    println("4. Print Record with Lowest Recorded Temperature")
    println("5. Print Record with Highest Recorded Temperature from a single month")
    println("6. Print Record with Lowest Recorded Temperature from a single month")
    println("7. Print Average of all precipitation from a single month")
    println()
    println("8. Quit \n")
    print("Please select an option: ")

return readln().toInt()

}

fun printAllRecords(weatherDB: MutableMap<String, Weather>) {
    for ((key, weatherRecord) in weatherDB) {
        weatherRecord.month.toString()
        weatherRecord.day.toString()
        println(" ${weatherRecord.month}".padStart(12) + " ${weatherRecord.day}".padStart(12) + " ${weatherRecord.sunrise}".padStart(12) + " ${weatherRecord.sunset}".padStart(12) + " ${weatherRecord.mean}".padStart(12) + " ${weatherRecord.avgHi}".padStart(12) + " ${weatherRecord.avgLo}".padStart(12) + " ${weatherRecord.recHi}".padStart(12) + " ${weatherRecord.recHiYr}".padStart(12) + " ${weatherRecord.recLo}".padStart(12) + " ${weatherRecord.recLoYr}".padStart(12) + " ${weatherRecord.avgPrecip}".padStart(12) )
    }
}

fun printOneMonthRecord(weatherDB: MutableMap<String, Weather>, month: Int) {
    for ((key, weatherRecord) in weatherDB) {
        if ( weatherRecord.month == month) {
            println(" ${weatherRecord.month}".padStart(12) + " ${weatherRecord.day}".padStart(12) + " ${weatherRecord.sunrise}".padStart(12) + " ${weatherRecord.sunset}".padStart(12) + " ${weatherRecord.mean}".padStart(12) + " ${weatherRecord.avgHi}".padStart(12) + " ${weatherRecord.avgLo}".padStart(12) + " ${weatherRecord.recHi}".padStart(12) + " ${weatherRecord.recHiYr}".padStart(12) + " ${weatherRecord.recLo}".padStart(12) + " ${weatherRecord.recLoYr}".padStart(12) + " ${weatherRecord.avgPrecip}".padStart(12) )
        }
    }
}

fun printHighestRecordTemp(weatherDB: MutableMap<String, Weather>) {
    val arrayOfRecHi = mutableListOf<Int>()
    for((key, weatherRecord) in weatherDB) {
        arrayOfRecHi +=  weatherRecord.recHi
    }

    val numMaxArray = arrayOfRecHi.max()
    for ((key, weatherRecord) in weatherDB) {
        if (weatherRecord.recHi == numMaxArray) {
            println(" ${weatherRecord.month}".padStart(12) + " ${weatherRecord.day}".padStart(12) + " ${weatherRecord.sunrise}".padStart(12) + " ${weatherRecord.sunset}".padStart(12) + " ${weatherRecord.mean}".padStart(12) + " ${weatherRecord.avgHi}".padStart(12) + " ${weatherRecord.avgLo}".padStart(12) + " ${weatherRecord.recHi}".padStart(12) + " ${weatherRecord.recHiYr}".padStart(12) + " ${weatherRecord.recLo}".padStart(12) + " ${weatherRecord.recLoYr}".padStart(12) + " ${weatherRecord.avgPrecip}".padStart(12) )
        }
    }
}

fun printLowestRecordTemp(weatherDB: MutableMap<String, Weather>) {
    val arrayOfRecLow = mutableListOf<Int>()
    for((key, weatherRecord) in weatherDB) {
        arrayOfRecLow +=  weatherRecord.recLo
    }

    val numMinArray = arrayOfRecLow.min()
    for ((key, weatherRecord) in weatherDB) {
        if (weatherRecord.recLo == numMinArray) {
            println(" ${weatherRecord.month}".padStart(12) + " ${weatherRecord.day}".padStart(12) + " ${weatherRecord.sunrise}".padStart(12) + " ${weatherRecord.sunset}".padStart(12) + " ${weatherRecord.mean}".padStart(12) + " ${weatherRecord.avgHi}".padStart(12) + " ${weatherRecord.avgLo}".padStart(12) + " ${weatherRecord.recHi}".padStart(12) + " ${weatherRecord.recHiYr}".padStart(12) + " ${weatherRecord.recLo}".padStart(12) + " ${weatherRecord.recLoYr}".padStart(12) + " ${weatherRecord.avgPrecip}".padStart(12) )
        }
    }
}

fun printRecordHighOneMonth(weatherDB: MutableMap<String, Weather>, month: Int) {
    val arrayOfRecHiMonth = mutableListOf<Int>()
    for ((key,weathRecord) in weatherDB){
        if(weathRecord.month == month) {
        arrayOfRecHiMonth += weathRecord.recHi
        }
    }

    val maxNumOfRecHi = arrayOfRecHiMonth.max()
    for ((key, weatherRecord) in weatherDB) {
        if(weatherRecord.month == month && weatherRecord.recHi == maxNumOfRecHi) {
            println(" ${weatherRecord.month}".padStart(12) + " ${weatherRecord.day}".padStart(12) + " ${weatherRecord.sunrise}".padStart(12) + " ${weatherRecord.sunset}".padStart(12) + " ${weatherRecord.mean}".padStart(12) + " ${weatherRecord.avgHi}".padStart(12) + " ${weatherRecord.avgLo}".padStart(12) + " ${weatherRecord.recHi}".padStart(12) + " ${weatherRecord.recHiYr}".padStart(12) + " ${weatherRecord.recLo}".padStart(12) + " ${weatherRecord.recLoYr}".padStart(12) + " ${weatherRecord.avgPrecip}".padStart(12))
        }
    }
}

fun printRecordLowOneMonth(weatherDB: MutableMap<String, Weather>, month: Int) {
    val arrayOfRecLowMonth = mutableListOf<Int>()
    for((key,weatherRecord) in weatherDB){
        if(weatherRecord.month == month) {
            arrayOfRecLowMonth += weatherRecord.recLo
        }
    }
    val minNumOfRecLo = arrayOfRecLowMonth.min()
    for((key,weatherRecord) in weatherDB) {
        if(weatherRecord.month == month && weatherRecord.recLo == minNumOfRecLo) {
            println(" ${weatherRecord.month}".padStart(12) + " ${weatherRecord.day}".padStart(12) + " ${weatherRecord.sunrise}".padStart(12) + " ${weatherRecord.sunset}".padStart(12) + " ${weatherRecord.mean}".padStart(12) + " ${weatherRecord.avgHi}".padStart(12) + " ${weatherRecord.avgLo}".padStart(12) + " ${weatherRecord.recHi}".padStart(12) + " ${weatherRecord.recHiYr}".padStart(12) + " ${weatherRecord.recLo}".padStart(12) + " ${weatherRecord.recLoYr}".padStart(12) + " ${weatherRecord.avgPrecip}".padStart(12))
        }
    }
}

fun printAveragePrecip(weatherDB: MutableMap<String, Weather>, month: Int) {
    val arrayOfPrecip = mutableListOf<Double>()
    for ((key,weatherRecord) in weatherDB) {
        if(weatherRecord.month == month){
            arrayOfPrecip += weatherRecord.avgPrecip
        }
    }
    val addUpArray = (arrayOfPrecip.sum()).toFloat()
    val avgPrecipForMonth = (addUpArray /arrayOfPrecip.size)
    // I included an option of a formatted answer. I learned about this type of formatting in CIS 146
    val roundedAvg = "%.2f".format(avgPrecipForMonth)

    var userMonthChoice = ""

    when(month){
        1 -> userMonthChoice = "January"
        2 -> userMonthChoice = "February"
        3 -> userMonthChoice = "March"
        4 -> userMonthChoice = "April"
        5 -> userMonthChoice = "May"
        6 -> userMonthChoice = "June"
        7 -> userMonthChoice = "July"
        8 -> userMonthChoice = "August"
        9 -> userMonthChoice = "September"
        10 -> userMonthChoice = "October"
        11 -> userMonthChoice = "November"
        12 -> userMonthChoice = "December"
        else -> println("Error: Please enter a valid month (1-12)")
    }

    println(" ".repeat(35) + "Average Precipitation for the month of ${userMonthChoice} is ${avgPrecipForMonth} inches or ${roundedAvg} inches rounded." + " ".repeat(35))
}

fun printHeader(firstLineArray: MutableList<String>) {
    return println("${firstLineArray[0]}".padStart(12) + " ${firstLineArray[1]}".padStart(12) + " ${firstLineArray[2]}".padStart(12) + " ${firstLineArray[3]}".padStart(12) + " ${firstLineArray[4]}".padStart(12) + " ${firstLineArray[5]}".padStart(12) + " ${firstLineArray[6]}".padStart(12) + " ${firstLineArray[7]}".padStart(12) + " ${firstLineArray[8]}".padStart(12) + " ${firstLineArray[9]}".padStart(12) + " ${firstLineArray[10]}".padStart(12) + " ${firstLineArray[11]}".padStart(12) +
            "\n" + (" " + "-".repeat(11)).repeat(12))
}