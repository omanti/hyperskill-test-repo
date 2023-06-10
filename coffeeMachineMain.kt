package machine

import kotlin.system.exitProcess
class CoffeeMachine(
    var water: Int = 400,
    var milk: Int = 540,
    var coffee: Int = 120,
    var cups: Int = 9,
    var money: Int = 550,

) {
    var action: String = "default"
        set(value) {
        }
    var state: String = "Choosing an action"
        set(value) {
            value = field



        }
}
val coffeeMachine = CoffeeMachine()

fun main() {
    while (true) {
        println("Write action (buy, fill, take, remaining, exit): ")
        coffeeMachine.action = readln()
    when (coffeeMachine.action) {
        "buy" -> {buyCoffee()
            coffeeMachine.state = "Buying a coffee"}
        "fill" -> fill()
        "take" -> {
            println("I gave you ${'$'}${coffeeMachine.money} of money")
            coffeeMachine.money = 0
        }
        "remaining" -> remaining()
        "exit" -> break
        else -> {
            println("unknown action");
            main()
        }

        }
    }
}

fun checkIng (waterX: Int, milkX: Int, coffeeX: Int, moneyX: Int) {
    if (coffeeMachine.water >= waterX
        && coffeeMachine.milk >= milkX
        && coffeeMachine.coffee >= coffeeX
        && coffeeMachine.cups >= 1) {
        coffeeMachine.water -= waterX
        coffeeMachine.milk -= milkX
        coffeeMachine.coffee -= coffeeX
        coffeeMachine.cups--
        coffeeMachine.money += moneyX
        println("I have enough resources, making you a coffee!")
    } else println("Not enough ingredients")
}

fun buyCoffee(): Any {
    println ("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    val typeOfCoffee = readln().toString()
    return when (typeOfCoffee) {
        "1" -> when {
            coffeeMachine.water >= 250 && coffeeMachine.coffee >= 16 && coffeeMachine.cups >= 1 -> {
                println("I have enough resources, making you a coffee!")
                coffeeMachine.water -= 250; coffeeMachine.coffee -= 16; coffeeMachine.cups--; coffeeMachine.money += 4
                main()
            }
            coffeeMachine.water < 250 -> {
                println("Sorry, not enough water!")
                main()
            }
            coffeeMachine.coffee < 16 -> {
                println("Sorry, not enough coffee!")
                main()
            }
            coffeeMachine.cups < 1 -> {
                println("Sorry, not enough cups!")
                main()
            }
            else -> main()
        }

        "2" -> when {
            coffeeMachine.water >= 350 && coffeeMachine.milk >= 75 && coffeeMachine.coffee >= 20 && coffeeMachine.cups >= 1 -> {
                println("I have enough resources, making you a coffee!");
                coffeeMachine.water -= 350; coffeeMachine.milk -= 75; coffeeMachine.coffee -= 20; coffeeMachine.cups--; coffeeMachine.money += 7
                main()
            }
            coffeeMachine.water < 350 -> {
                println("Sorry, not enough water!")
                main()
            }
            coffeeMachine.milk < 75 -> {
                println("Sorry, not enough milk!")
                main()
            }
            coffeeMachine.coffee < 20 -> {
                println("Sorry, not enough coffee!")
                main()
            }
            coffeeMachine.cups < 1 -> {
                println("Sorry, not enough cups!")
                main()
            }
            else -> main()
        }
        "3" -> when {
            coffeeMachine.water >= 200 && coffeeMachine.milk >= 100 && coffeeMachine.coffee >= 12 && coffeeMachine.cups >= 1 -> {
                println("I have enough resources, making you a coffee!");
                coffeeMachine.water -= 200; coffeeMachine.milk -= 100; coffeeMachine.coffee -= 12; coffeeMachine.cups--; coffeeMachine.money += 6
                main()
            }
            coffeeMachine.water < 200 -> {
                println("Sorry, not enough water!")
                main()
            }
            coffeeMachine.milk < 100 -> {
                println("Sorry, not enough milk!")
                main()
            }
            coffeeMachine.coffee < 12 -> {
                println("Sorry, not enough coffee!")
                main()
            }
            coffeeMachine.cups < 1 -> {
                println("Sorry, not enough cups!")
                main()
            }
            else -> main()
        }
                "back" -> main()
                else -> {
                    println("unknown type of coffee");
                    main()
                }
            }
}
fun fill() {
    println("Write how many ml of water you want to add:")
    coffeeMachine.water += readln().toInt()
    println("Write how many ml of milk you want to add:")
    coffeeMachine.milk += readln().toInt()
    println("Write how many grams of coffee beans you want to add:")
    coffeeMachine.coffee += readln().toInt()
    println("Write how many disposable cups you want to add:")
    coffeeMachine.cups += readln().toInt()
    return main()
}
fun remaining() {
    println("""The coffee machine has:
$coffeeMachine.water ml of water
$coffeeMachine.milk ml of milk
$coffeeMachine.coffee g of coffee beans
$coffeeMachine.cups disposable cups
${'$'}$coffeeMachine.money of money""")
    main()
}