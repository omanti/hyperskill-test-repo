import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val alphabet = "abcdefghijklmnopqrstuvwxyz".toMutableList()
    val alphabetUppercase = "abcdefghijklmnopqrstuvwxyz".uppercase().toMutableList()
    val alphabetShifted = alphabet.toMutableList()
    val alphabetUppercaseShifted = alphabetUppercase.toMutableList()
    val action = if (args.contains("-mode")) {
        args[args.indexOf("-mode") + 1]
    } else {
        println("Error")
        exitProcess(0)
    }
    var data = if (args.contains("-data")) args[args.indexOf("-data") + 1].toMutableList() else "".toMutableList()
    val positions = if (args[args.indexOf("-key") + 1] == "") {
        0
    } else {
        args[args.indexOf("-key") + 1].toInt()
    }
    shiftAlphabet(alphabetShifted, alphabetUppercaseShifted, positions)
    val inArg = args[args.indexOf("-in") + 1]
    val outArg = args[args.indexOf("-out") + 1]
    val algorithm = args[args.indexOf("-alg") + 1]
    if (data.isEmpty() && inArg.isEmpty()) data = "".toMutableList() // нет data и in пустой -> пустая строка на вводе
    if (data.isEmpty() && inArg.isNotEmpty()) data =  File(inArg).readText().toMutableList()
    if (action == "dec" && algorithm == "unicode") { //выбор действия
        decUnicode(data, positions, outArg)
    } else if (action == "enc" && algorithm == "unicode") {
        encUnicode(data, positions, outArg)
    } else if (action == "dec" && algorithm == "shift" || algorithm.isEmpty()) {
        decShift(data, alphabet, alphabetUppercase, alphabetShifted, alphabetUppercaseShifted, outArg)
    } else {
        encShift(data, alphabet, alphabetUppercase, alphabetShifted, alphabetUppercaseShifted, outArg)
    }
}
fun shiftAlphabet(alphabetShifted: MutableList<Char>, alphabetUppercaseShifted: MutableList<Char>, positions: Int) {
    val shiftAmount = if (positions >= alphabetShifted.size) {
        positions % alphabetShifted.size
    } else {
        positions
    }
    val tempList = alphabetShifted.toMutableList()
    for (i in 0 until alphabetShifted.size) {
        val newPosition = (i + shiftAmount) % alphabetShifted.size
        alphabetShifted[newPosition] = tempList[i]
    }
    val tempListUppercase = alphabetUppercaseShifted.toMutableList()
    for (i in 0 until alphabetUppercaseShifted.size) {
        val newPosition = (i + shiftAmount) % alphabetUppercaseShifted.size
        alphabetUppercaseShifted[newPosition] = tempListUppercase[i]
    }
}
fun encShift(data: MutableList<Char>,
                  alphabet: MutableList<Char>,
                  alphabetUppercase: MutableList<Char>,
                  alphabetShifted: MutableList<Char>,
                  alphabetUppercaseShifted: MutableList<Char>,
                  outArg: String) {
    val encryptedMsg = mutableListOf<Char>()
    for (char in data) {
        if (char.isUpperCase()) {
            val index = alphabetUppercaseShifted.indexOf(char)
            if (index != -1) {
                encryptedMsg.add(alphabetUppercase[index])
            } else {
                encryptedMsg.add(char)
            }
        } else {
            val index = alphabetShifted.indexOf(char)
            if (index != -1) {
                encryptedMsg.add(alphabet[index])
            } else {
                encryptedMsg.add(char)
            }
        }
    }
    if (outArg.isEmpty()) {
        println(encryptedMsg.joinToString(separator = ""))
    } else {
        File(outArg).writeText(encryptedMsg.joinToString(separator = ""))
    }
}
fun decShift(data: MutableList<Char>,
             alphabet: MutableList<Char>,
             alphabetUppercase: MutableList<Char>,
             alphabetShifted: MutableList<Char>,
             alphabetUppercaseShifted: MutableList<Char>,
             outArg: String) {
    val decryptedMsg = mutableListOf<Char>()
    for (char in data) {
        if (char.isUpperCase()) {
            val index = alphabetUppercase.indexOf(char)
            if (index != -1) {
                decryptedMsg.add(alphabetUppercaseShifted[index])
            } else {
                decryptedMsg.add(char)
            }
        } else {
            val index = alphabet.indexOf(char)
            if (index != -1) {
                decryptedMsg.add(alphabetShifted[index])
            } else {
                decryptedMsg.add(char)
            }
        }
    }
    if (outArg.isEmpty()) {
        println(decryptedMsg.joinToString(separator = ""))
    } else {
        File(outArg).writeText(decryptedMsg.joinToString(separator = ""))
    }
}
fun encUnicode(data: MutableList<Char>, positions: Int, outArg: String) {
    val encryptedMsg = mutableListOf<Char>()
    for (char in data) {
        val code = char.code + positions
        encryptedMsg.add(code.toChar())
    }
    if (outArg.isEmpty()) {
        println(encryptedMsg.joinToString(separator = ""))
    } else {
        File(outArg).writeText(encryptedMsg.joinToString(separator = ""))
    }
}
fun decUnicode(data: MutableList<Char>, positions: Int, outArg: String) {
    val decryptedMsg = mutableListOf<Char>()
    for (char in data) {
        val code = char.code - positions
        decryptedMsg.add(code.toChar())
    }
    if (outArg.isEmpty()) {
        println(decryptedMsg.joinToString(separator = ""))
    } else {
        File(outArg).writeText(decryptedMsg.joinToString(separator = ""))
    }
}
