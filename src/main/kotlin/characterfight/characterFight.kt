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


var dice4 = Dice(4)
var dice8 = Dice(8)
var dice15 = Dice(15)
var dice10 = Dice(10)

fun main() {
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

                fighterOne.currentHit = (fighterOne.strength * (1.0/dice4.roll()) + (fighterOne.weapon.damageHits)/(dice8.roll())).toInt()
                println(fighterOne.currentHit)

            }
        }
    }while (choice != 4)
}

////    hit = (strength * (1.0/roll4) + (weapon hits)/roll8) (as an integer)

/*
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
