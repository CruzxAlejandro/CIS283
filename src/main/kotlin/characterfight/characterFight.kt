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
            println("Invalid Number - Must be a whole number between ${range.first} and ${range.last}")
            firstTime = false
        }
        print(message)
        input = readLine()
    } while (input.isNullOrEmpty() || ! isInt.matches(input) || input.toInt() !in range)
    return input.toInt()
}

class Menu(var menuItems: Array<String>, var prompt : String) {
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

fun characterLoad(fileOne: String) : Character {
    var fighterOneFile = "src/main/kotlin/characterfight/${fileOne}"
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
    var playerHit = 1
    var playerWeapon = Weapon(ListTwo[0], ListTwo[1].toInt())
    var playerArmor = Armor(ListThree[0], ListThree[1].toInt())
    var player = Character(name = ListOne[0], ListOne[1], ListOne[2].toInt(), playerHit, ListOne[3].toInt(), ListOne[4].toInt(), playerWeapon, playerArmor)
    return player
}

fun main() {
    var dice4 = Dice(4)
    var dice8 = Dice(8)
    var dice15 = Dice(15)
    var dice10 = Dice(10)

    val menu = Menu(arrayOf("Load Character 1", "Load Character 2", "Fight", "Quit"), "Please make a selection: ")
    var fighterOneFile = ""
    var fighterTwoFile = ""
    do {
        val choice = menu.displayMenu()
        when(choice) {
            1 -> {
                print("Enter the name of a character file: ")
                fighterOneFile = readLine().toString()
                var fighterOne = characterLoad(fighterOneFile)
                menu.menuItems[0] = "${fighterOne.name} has been loaded."
            }
            2 -> {
                print("Enter the name of a character file: ")
                fighterTwoFile = readLine().toString()
                var fighterTwo = characterLoad(fighterTwoFile)
                menu.menuItems[1] = "${fighterTwo.name} has been loaded."
            }
            3 -> {
                // creating Characters for Legolas and Gimli
                var fighterOne = characterLoad(fighterOneFile)
                var fighterTwo = characterLoad(fighterTwoFile)
                // players alive
                var fighterOneAlive = true
                var fighterTwoAlive = true
                // Who goes first
                var fighterOneAgileDice = Dice(fighterOne.agility)
                var fighterTwoAgileDice = Dice(fighterTwo.agility)
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
                if (legolasFirst) {
                    var pattern = """\r""".toRegex()
                    do {
                        var legolasHit = dice10.roll()
                        if (legolasHit < fighterOne.agility ) {
                            fighterOne.currentHit = (fighterOne.strength * (1.0/dice4.roll()) + (fighterOne.weapon.damageHits)/(dice8.roll())).toInt()
                            var armorSave = (fighterTwo.armor.protectionHits / dice15.roll()).toInt()
                            var damageDone = (fighterOne.currentHit - armorSave)
                            if (damageDone <= 0 ) {
                                damageDone = 0
                            }
                            var finalHealth = fighterOne.hitPoints - damageDone
                            if(finalHealth <= 0) {
                                finalHealth = 0
                            }
                            println("${fighterOne.name} fights with the ${fighterOne.weapon.name}:")
                            println(" ".repeat(12) + "Hit:" + "${fighterOne.currentHit}".padStart(3) + " ".repeat(4) + "${fighterTwo.name}'s armor saved ${armorSave} points" )
                            println("${fighterTwo.name}s hits are reduced by ${damageDone} points.")
                            println("${fighterTwo.name} has ${finalHealth} out of ${fighterTwo.hitPoints}.")

                            if (finalHealth <= 0) {
                                fighterTwoAlive = false
                            }

                        } else {
                            println("Legolas misses!")
                        }
//                        var gimliHit = dice10.roll()
//                        if (gimliHit < fighterTwo.agility) {
//                            fighterTwo.currentHit = (fighterTwo.strength * (1.0/dice4.roll()) + (fighterTwo.weapon.damageHits)/(dice8.roll())).toInt()
//                            var armorSave = (fighterOne.armor.protectionHits / dice15.roll()).toInt()
//                            var damageDone = (fighterTwo.currentHit - armorSave)
//                            if (damageDone <= 0) {
//                                damageDone = 0
//                            }
//                            var finalHealth = fighterOne.hitPoints - damageDone
//                            if(finalHealth <= 0) {
//                                finalHealth = 0
//                            }
//                            println("${fighterTwo.name} fights with the ${fighterTwo.weapon.name}:")
//                            println(" ".repeat(12) + "Hit:" + "${fighterTwo.currentHit}".padStart(3) + " ".repeat(4) + "${fighterOne.name}'s armor saved ${armorSave} points" )
//                            println("${fighterOne.name}s hits are reduced by ${damageDone} points.")
//                            println("${fighterOne.name} has ${finalHealth} out of ${fighterOne.hitPoints}.")
//                            fighterOne.hitPoints = finalHealth
//                            if (fighterOne.hitPoints <= 0) {
//                                fighterOneAlive = false
//                            }
//                        } else {
//                            println("Gimli misses!")
//                        }
                        println("Hit return to continue...")
                        var nextRound = readLine().toString();
                    }while (fighterOneAlive && fighterTwoAlive && !nextRound.matches(pattern))
                }

            }
        }
    }while (choice != 4)
}
/*


Loop fighting until one character has lost all of their hit points (DIES)
For each fighter
First roll an AGILE sided die for each fighter to determine which fighter goes first. The fighter with the highest score will go first.
Roll a 10-sided die to determine if the fighter hits or misses
The equation would be: roll10 < agility is a hit otherwise a miss.
A hits damage is determined by the character's strength and weapon's power with this formula:
hit = (strength * (1.0/roll4) + (weapon hits)/roll8) (as an integer)
This is then reduced by an armor save from the formula:
armor_save = (opponent's armor hits / roll15 ) (as an integer)
Reduce the opponent's current_hits by the (hit – armor_save) amount (Don't reduce by negative numbers)
End fighting loop (now do the opponent)
PAUSE after each round and print both fighter’s statistics
Print out the winner and both fighter's statistics



Dave, elf, 47, 30, 10      #Name, race, hits, strength, agility turn into a Character Object
Bow, 15                    #name, hits   turn into a Weapon Object
Leather, 10                #name, hits   turn into an Armor Object

Character Class

    Maximum values should be private val variables and should  be checked on input so they cannot be used for cheating

    Name, Race,

    Hit Points (Min 1, Max 100),

    Current hit points (calculated)

    Strength (Min 1, Max 50),

    Agility (Min 1, Max 10),

    Weapon,   (object from the Weapon Class)

    Armor  (object from the Armor Class)


 */
