package characterfight
import java.io.File

/************************************************************
 *  Name:         Alex Cruz
 *  Date:         5/4/23
 *  Assignment:   Character Fight
 *  Class Number: CIS 283
 *  Description:
 ************************************************************/

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
        print(message)
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
        return promptForInt(prompt, 1..menuItems.size )
    }
}
class Dice(private var numSides : Int) {
    fun roll() : Int {
        return (1..numSides).random()
    }
}
class Character(var name:String, var race:String, var hitPoints:Int, var currentHit: Int, var strength: Int, var agility:Int, var weapon: Weapon, var armor: Armor) {
val maxFifty = 50
val maxHundred = 100
val minOne = 1
init {
    if (hitPoints >= maxHundred) {
        hitPoints = 100
    }
    if (hitPoints <= minOne) {
        hitPoints = 1
    }
    if (strength >= maxFifty) {
        strength = 50
    }
    if (strength <= minOne) {
        strength = 1
    }
    if (agility >= maxFifty) {
        agility = 50
    }
    if (agility <= minOne) {
        agility = 1
    }
    if (currentHit <= 0) {
        currentHit = 0
    }
}
fun reviveCharater(healthBack: Int) {
    currentHit = healthBack
}

fun reduceHits(roundHit: Int, armorSave: Int) : Int {

    var damageDone = roundHit - armorSave
    if (damageDone <= 0 ) {
        damageDone = 0
    }
    return damageDone
}

override fun toString():String {
        return """
        |Name: $name
        |Race: $race
        |Hit Points: $hitPoints
        |Current Hit: $currentHit
        |Strength: $strength
        |Agility: $agility   
        """.trimMargin()
    }
}
open class Item(var name:String){
    override fun toString():String {
        return """
        |Name: $name    
        """.trimMargin()
    }
}
class Weapon(name:String, var damageHits:Int): Item(name){
    override fun toString():String {
    return super.toString() +
            """
            |
            |DamageHits: $damageHits                
            """.trimMargin()
    }
}
class Armor(name: String, var protectionHits:Int): Item(name){
    override fun toString():String {
        return super.toString() +
            """
            |
            |Protection hits: $protectionHits                
            """.trimMargin()
    }
}
fun characterLoad(file: String) : Character {
    var fighterOneFile = "src/main/kotlin/characterfight/${file}"
    var ListOne = arrayListOf<String>()
    var ListTwo = arrayListOf<String>()
    var ListThree = arrayListOf<String>()
    val lines = File(fighterOneFile).readLines()
    var firstLine = true
    var secondLine = true
    var pattern = """,\s|,""".toRegex()
    for (line in lines) {
        if (firstLine){
            var lineOne = line.split(pattern)
            ListOne += lineOne
            firstLine = false
        }
        else if (secondLine) {
            var lineTwo = line.split(pattern)
            ListTwo += lineTwo
            secondLine = false
        }
        else {
            var lineThree = line.split(pattern)
            ListThree += lineThree
            secondLine = false
        }
    }
    var playerWeapon = Weapon(ListTwo[0], ListTwo[1].toInt())
    var playerArmor = Armor(ListThree[0], ListThree[1].toInt())
    var player = Character(name = ListOne[0], ListOne[1], ListOne[2].toInt(), ListOne[2].toInt(), ListOne[3].toInt(), ListOne[4].toInt(), playerWeapon, playerArmor)
    return player
}

fun main() {
    var dice4 = Dice(4)
    var dice8 = Dice(8)
    var dice15 = Dice(15)
    var dice10 = Dice(10)
    var pattern = """\r""".toRegex()
    var nextRound = ""

    lateinit var fighterOne : Character
    lateinit var fighterTwo: Character

    val menu = Menu(arrayOf("Load Character 1", "Load Character 2", "Fight", "Quit"), "Please make a selection: ")
    var fighterOneFile = ""
    var fighterTwoFile = ""
    do {
        var choice = menu.displayMenu()
        when(choice) {
            1 -> {
//                print("Enter the name of a character file: ")
//                fighterOneFile = readLine().toString()
                fighterOneFile = promptForString("Enter the name of a character file: ")
                fighterOne = characterLoad(fighterOneFile)
                menu.menuItems[0] = "${fighterOne.name} has been loaded."
            }
            2 -> {
//                print("Enter the name of a character file: ")
//                fighterTwoFile = readLine().toString()
                fighterTwoFile = promptForString("Enter the name of a character file: ")
                fighterTwo = characterLoad(fighterTwoFile)
                menu.menuItems[1] = "${fighterTwo.name} has been loaded."
            }
            3 -> {
                if (!fighterOneFile.isNullOrEmpty() && !fighterTwoFile.isNullOrEmpty()) {
    //                var fighterOne = characterLoad(fighterOneFile)
    //                var fighterTwo = characterLoad(fighterTwoFile)
                    // players alive
                    fighterOne.reviveCharater(fighterOne.hitPoints)
                    fighterTwo.reviveCharater(fighterTwo.hitPoints)
                    var fighterOneAlive = true
                    var fighterTwoAlive = true
                    var fighterOneAgileDice = Dice(fighterOne.agility)
                    var fighterTwoAgileDice = Dice(fighterTwo.agility)
                    var round = 1
                    do {
                        var rollOne = fighterOneAgileDice.roll()
                        var rollTwo = fighterTwoAgileDice.roll()
                        var legolasFirst = false
                        var gimliFirst = false
                        if (rollOne > rollTwo) {
                            legolasFirst = true
                        }
                        else if (rollOne == rollTwo) {
                            legolasFirst = true
                        }
                        else {
                            gimliFirst = true
                        }
                        // Legolas goes first
//                        println("-".repeat(25) + "Round" + "${round}".padStart(3) + "-".repeat(25))
                        println("""
                                    |${" "}
                                    |${"-".repeat(25)} ROUND ${"${round}".padStart(2)} ${"-".repeat(25)}
                                    """.trimMargin())
                        if (legolasFirst) {
                                var legolasHit = dice10.roll()
                                if (legolasHit < fighterOne.agility) {
                                    var roundHit = (fighterOne.strength * (1.0/dice4.roll()) + (fighterOne.weapon.damageHits)/(dice8.roll())).toInt()
                                    var armorSave = (fighterTwo.armor.protectionHits / dice15.roll()).toInt()
                                    if (fighterTwo.currentHit <= 0) {
                                        fighterTwo.currentHit = 0
                                        fighterTwoAlive = false
                                    }
                                    fighterTwo.currentHit = fighterTwo.currentHit - fighterTwo.reduceHits(roundHit, armorSave)
                                    if (fighterTwo.currentHit <= 0) {
                                        fighterTwo.currentHit = 0
                                        fighterTwoAlive = false
                                    }
                                    println("${fighterOne.name} fights with the ${fighterOne.weapon.name}:")
                                    println(" ".repeat(12) + "Hit:" + "${roundHit}".padStart(3) + " ".repeat(4) + "${fighterTwo.name}'s armor saved ${armorSave} points" )
                                    println("${fighterTwo.name}s hits are reduced by ${fighterTwo.reduceHits(roundHit,armorSave)} points.")
                                    println("${fighterTwo.name} has ${fighterTwo.currentHit} out of ${fighterTwo.hitPoints}.")


                                } else {
                                    println("${fighterOne.name} misses!")
                                }
                                var gimliHit = dice10.roll()
                                if (gimliHit < fighterTwo.agility && fighterTwoAlive) {
                                    var roundHit = (fighterTwo.strength * (1.0/dice4.roll()) + (fighterTwo.weapon.damageHits)/(dice8.roll())).toInt()
                                    var armorSave = (fighterOne.armor.protectionHits / dice15.roll()).toInt()
                                    fighterOne.currentHit = fighterOne.currentHit - fighterOne.reduceHits(roundHit, armorSave)
                                    if (fighterOne.currentHit <= 0) {
                                        fighterOne.currentHit = 0
                                        fighterOneAlive = false
                                    }
                                    println("${fighterTwo.name} fights with the ${fighterTwo.weapon.name}:")
                                    println(" ".repeat(12) + "Hit:" + "${roundHit}".padStart(3) + " ".repeat(4) + "${fighterOne.name}'s armor saved ${armorSave} points" )
                                    println("${fighterOne.name}s hits are reduced by ${fighterOne.reduceHits(roundHit,armorSave)} points.")
                                    println("${fighterOne.name} has ${fighterOne.currentHit} out of ${fighterOne.hitPoints}.")

                                } else {
                                    if (fighterTwoAlive) {
                                        println("${fighterTwo.name} misses!")
                                    }
                                }
                        }
                        if (gimliFirst) {
                            var gimliHit = dice10.roll()
                            if (gimliHit < fighterTwo.agility && fighterTwoAlive) {
                                var roundHit = (fighterTwo.strength * (1.0/dice4.roll()) + (fighterTwo.weapon.damageHits)/(dice8.roll())).toInt()
                                var armorSave = (fighterOne.armor.protectionHits / dice15.roll()).toInt()
                                fighterOne.currentHit = fighterOne.currentHit - fighterOne.reduceHits(roundHit, armorSave)
                                if (fighterOne.currentHit <= 0) {
                                    fighterOne.currentHit = 0
                                    fighterOneAlive = false
                                }
                                println("${fighterTwo.name} fights with the ${fighterTwo.weapon.name}:")
                                println(" ".repeat(12) + "Hit:" + "${roundHit}".padStart(3) + " ".repeat(4) + "${fighterOne.name}'s armor saved ${armorSave} points" )
                                println("${fighterOne.name}s hits are reduced by ${fighterOne.reduceHits(roundHit,armorSave)} points.")
                                println("${fighterOne.name} has ${fighterOne.currentHit} out of ${fighterOne.hitPoints}.")

                            } else {
                                if (fighterTwoAlive) {
                                    println("${fighterTwo.name} misses!")
                                }
                            }
                            var legolasHit = dice10.roll()
                            if (legolasHit < fighterOne.agility) {
                                var roundHit = (fighterOne.strength * (1.0/dice4.roll()) + (fighterOne.weapon.damageHits)/(dice8.roll())).toInt()
                                var armorSave = (fighterTwo.armor.protectionHits / dice15.roll()).toInt()
                                if (fighterTwo.currentHit <= 0) {
                                    fighterTwo.currentHit = 0
                                    fighterTwoAlive = false
                                }
                                fighterTwo.currentHit = fighterTwo.currentHit - fighterTwo.reduceHits(roundHit, armorSave)
                                if (fighterTwo.currentHit <= 0) {
                                    fighterTwo.currentHit = 0
                                    fighterTwoAlive = false
                                }
                                println("${fighterOne.name} fights with the ${fighterOne.weapon.name}:")
                                println(" ".repeat(12) + "Hit:" + "${roundHit}".padStart(3) + " ".repeat(4) + "${fighterTwo.name}'s armor saved ${armorSave} points" )
                                println("${fighterTwo.name}s hits are reduced by ${fighterTwo.reduceHits(roundHit,armorSave)} points.")
                                println("${fighterTwo.name} has ${fighterTwo.currentHit} out of ${fighterTwo.hitPoints}.")


                            } else {
                                if (fighterOneAlive) {
                                    println("${fighterOne.name} misses!")
                                }
                            }
                        }
                        if (fighterOneAlive && fighterTwoAlive) {
                            println("""
                               |Hit return to continue ...
                               |${"-".repeat(60)}
                                """.trimMargin())
                            nextRound = readLine().toString();
                        }
                        round += 1
                    }while (fighterOneAlive && fighterTwoAlive && !nextRound.matches(pattern))
                    if (!fighterOneAlive) {
                        println("""
                                    |${"-".repeat(60)}
                                    |${" "}
                                    |${fighterTwo.name} WINS!
                                    |${"-".repeat(60)}
                                    |${fighterOne.name} has ${fighterOne.currentHit} out of ${fighterOne.hitPoints}
                                    |${fighterTwo.name} has ${fighterTwo.currentHit} out of ${fighterTwo.hitPoints}
                                    |${"-".repeat(60)}
                                """.trimMargin())
                    }
                    if (!fighterTwoAlive) {
                        println("""
                                    |${"-".repeat(60)}
                                    |${" "}
                                    |${fighterOne.name} WINS!
                                    |${"-".repeat(60)}                            
                                    |${fighterTwo.name} has ${fighterTwo.currentHit} out of ${fighterTwo.hitPoints}
                                    |${fighterOne.name} has ${fighterOne.currentHit} out of ${fighterOne.hitPoints}
                                    |${"-".repeat(60)}
                                """.trimMargin())
                    }
                }
                else {
                    println("""|${" "}
                               |ERROR
                               |${"-".repeat(50)}
                               |Please make sure two character files are loaded...
                               |${"-".repeat(50)}
                               |${" "}
                                """.trimMargin())
                }
            }
        }
    }while (choice != menu.quit)
}