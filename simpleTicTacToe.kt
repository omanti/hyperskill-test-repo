package tictactoe
import java.util.Scanner
var turnCounter = 1

fun main() {
    val scanner = Scanner(System.`in`)
    val mutList2D = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )
    var exitRequested = false
    printBoard(mutList2D)
    while (!exitRequested) {
        val conditions = checkConditions(mutList2D)
        if (!conditions.first) {
            println(conditions.second)
            exitRequested = true
            break
        }
        println("Please, make a move:")
        makePlayerMove(scanner, mutList2D)
    }
    scanner.close()
}
fun printBoard(mutList2D: MutableList<MutableList<Char>>) {
    println("---------")
    for (row in mutList2D) {
        println("| ${row.joinToString(separator = " ")} |")
    }
    println("---------")
}
fun makePlayerMove(scanner: Scanner, mutList2D: MutableList<MutableList<Char>>) {
    if (scanner.hasNextInt()) {
        val coordinateString = scanner.nextInt()
        val coordinatePosition = scanner.nextInt()
        if (isCoordinateOutOfRange(coordinateString, coordinatePosition)) {
            println("Coordinates should be from 1 to 3!")
            println("Please, make a move:")
            makePlayerMove(scanner, mutList2D)
        } else if (isCellOccupied(mutList2D, coordinateString, coordinatePosition)) {
            println("This cell is occupied! Choose another one!")
            println("Please, make a move:")
            makePlayerMove(scanner, mutList2D)
        } else if (turnCounter % 2 == 1) {
            makeMoveX(mutList2D, coordinateString, coordinatePosition)
            printBoard(mutList2D)
            if (!checkConditions(mutList2D).first)
                println(checkConditions(mutList2D).second)
        } else if (turnCounter % 2 == 0) {
            makeMoveO(mutList2D, coordinateString, coordinatePosition)
            printBoard(mutList2D)
            if (!checkConditions(mutList2D).first)
                println(checkConditions(mutList2D).second)
        }
    } else {
        println("You should enter numbers!")
        println("Please, make a move:")
        makePlayerMove(scanner, mutList2D)
    }
}
fun isCoordinateOutOfRange(coordinateString: Int, coordinatePosition: Int): Boolean {
    return (coordinateString < 1 || coordinateString > 3) || (coordinatePosition < 1 || coordinatePosition > 3)
}
fun isCellOccupied(mutList2D: MutableList<MutableList<Char>>, coordinateString: Int, coordinatePosition: Int): Boolean {
    return mutList2D[coordinateString - 1][coordinatePosition - 1] == 'X' ||
            mutList2D[coordinateString - 1][coordinatePosition - 1] == 'O'
}
fun makeMoveX(mutList2D: MutableList<MutableList<Char>>, coordinateString: Int, coordinatePosition: Int) {
    mutList2D[coordinateString - 1][coordinatePosition - 1] = 'X'
    turnCounter++
    return
}
fun makeMoveO(mutList2D: MutableList<MutableList<Char>>, coordinateString: Int, coordinatePosition: Int) {
    mutList2D[coordinateString - 1][coordinatePosition - 1] = 'O'
    turnCounter++
    return
}
fun checkConditions(mutList2D: MutableList<MutableList<Char>>): Pair<Boolean, String> {
    var xCounter = 0
    var oCounter = 0
    var emptyCounter = 0
    var x = 'X'
    var o = 'O'
    if (mutList2D[0][0] == o && mutList2D[0][1] == o && mutList2D[0][2] == o || // rows
        mutList2D[1][0] == o && mutList2D[1][1] == o && mutList2D[1][2] == o ||
        mutList2D[2][0] == o && mutList2D[2][1] == o && mutList2D[2][2] == o ||
        mutList2D[0][2] == o && mutList2D[1][1] == o && mutList2D[2][0] == o || // diagonals
        mutList2D[0][0] == o && mutList2D[1][1] == o && mutList2D[2][2] == o ||
        mutList2D[0][0] == o && mutList2D[1][0] == o && mutList2D[2][0] == o || // columns
        mutList2D[0][1] == o && mutList2D[1][1] == o && mutList2D[2][1] == o ||
        mutList2D[0][2] == o && mutList2D[1][2] == o && mutList2D[2][2] == o
    ) o = 't'
    if (mutList2D[0][0] == x && mutList2D[0][1] == x && mutList2D[0][2] == x || // rows
        mutList2D[1][0] == x && mutList2D[1][1] == x && mutList2D[1][2] == x ||
        mutList2D[2][0] == x && mutList2D[2][1] == x && mutList2D[2][2] == x ||
        mutList2D[0][2] == x && mutList2D[1][1] == x && mutList2D[2][0] == x || // diagonals
        mutList2D[0][0] == x && mutList2D[1][1] == x && mutList2D[2][2] == x ||
        mutList2D[0][0] == x && mutList2D[1][0] == x && mutList2D[2][0] == x || // columns
        mutList2D[0][1] == x && mutList2D[1][1] == x && mutList2D[2][1] == x ||
        mutList2D[0][2] == x && mutList2D[1][2] == x && mutList2D[2][2] == x
    ) x = 't'
    for (list in mutList2D) {
        for (i in list) {
            when (i) {
                'X' -> xCounter++
                'O' -> oCounter++
                ' ' -> emptyCounter++
            }
        }
    }
    if (xCounter - oCounter > 1 || oCounter - xCounter > 1 || (o == 't' && x == 't')) {
        return Pair(false, "Impossible")
    } else if (emptyCounter == 0 && x != 't' && o != 't') {
        return Pair(false, "Draw")
    }
    else if (x == 't') {
        return Pair(false, "X wins")
    } else if (o == 't') {
        return Pair(false, "O wins")
    } else {
        return Pair(true, "")
    }
}