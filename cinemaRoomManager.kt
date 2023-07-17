package cinema
import java.util.Scanner
class Cinema(
    var rows: Int = 0,
    var seats: Int = 0,
    var currentIncome: Int = 0,
    var purchasedTickets: Int = 0,
    var totalIncome: Int = 0,
    var hall: MutableList<MutableList<String>> = mutableListOf(
        mutableListOf(),
        mutableListOf()
    )
)
val cinema = Cinema()
var exitRequested = false
fun main() {
    val scanner = Scanner(System.`in`)
    if (cinema.rows == 0) {
        println("Enter the number of rows:")
        cinema.rows = scanner.nextInt()
        println("Enter the number of seats in each row:")
        cinema.seats = scanner.nextInt()
        cinema.hall = MutableList(cinema.rows) { MutableList(cinema.seats) { "S" } }
        if (cinema.rows * cinema.seats > 60 && cinema.rows % 2 == 1) {//(cinema.rows * cinema.seats < 60) {
           cinema.totalIncome = cinema.rows / 2 * cinema.seats * 10 + (cinema.rows / 2 + 1) * cinema.seats * 8
        } else if (cinema.rows * cinema.seats > 60 && cinema.rows % 2 == 0) {
            cinema.totalIncome = cinema.rows / 2 * cinema.seats * 10 + cinema.rows / 2 * cinema.seats * 8
        } else {
            cinema.totalIncome = cinema.rows * cinema.seats * 10
        }
    }
    while (!exitRequested) {
        println("""1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""")
        when (readln().toInt()) {
            1 -> printCinema()
            2 -> buyTicket()
            3 -> statistics()
            0 -> exitRequested = true
            else -> println("Unknown action")
        }
    }
}
fun statistics() {
    val percentage = (cinema.purchasedTickets / (cinema.rows * cinema.seats / 100.00))
    val formatPercentage = "%.2f".format(percentage)
    println("Number of purchased tickets: ${cinema.purchasedTickets}")
    println("Percentage: $formatPercentage%")
    println("Current income: \$${cinema.currentIncome}")
    println("Total income: \$${cinema.totalIncome}")
    main()
}
fun buyTicket() {
    val scanner = Scanner(System.`in`)
    println("Enter a row number:")
    var requestedRow = scanner.nextInt()
    while (requestedRow > cinema.rows) {
        println("Wrong input!!")
        println("Enter a row number:")
        requestedRow = scanner.nextInt()
    }
    println("Enter a seat number in that row:")
    var requestedSeat = scanner.nextInt()
    while (requestedSeat > cinema.seats) {
        println("Wrong input!")
        println("Enter a seat number in that row:")
        requestedSeat = scanner.nextInt()
    }
    if(cinema.hall[requestedRow - 1][requestedSeat - 1] == "B") {
        println("That ticket has already been purchased!")
        buyTicket()
    }
    val ticketPrice = if (cinema.rows * cinema.seats > 60) {
        if (cinema.rows % 2 == 1 && requestedRow <= cinema.rows / 2) {
            10
        } else 8
    } else 10
    cinema.purchasedTickets++
    cinema.currentIncome += ticketPrice
    println("Ticket price: $$ticketPrice")
    cinema.hall[requestedRow - 1][requestedSeat - 1] = "B"
    main()
}
fun printCinema() {
    println("Cinema:")
    print("  ")
    repeat(cinema.seats) { print("${it + 1} ") }
    println()
    cinema.hall.forEachIndexed { rowIndex, row ->
        print("${rowIndex + 1} ")
        row.forEach { print("$it ") }
        println()
    }
    main()
}