import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val columns = readLine()!!.toInt()
    print("\n")
    var seats = emptyArray<Array<String>>()
    var rowOf = emptyArray<String>()
    
    for (i in 0..rows - 1) {
        for (j in 0..columns - 1) {
            rowOf += "S"
        }
        seats += rowOf
        rowOf = emptyArray<String>()
    }
    
    
    var cost = 0
    var currentCost = 0
    var totalCost = 0
    if (rows * columns <= 60) {
        totalCost = rows * columns * 10
    } else {
        if (rows % 2 != 0) {
            totalCost = ((rows - 1) / 2) * 10 * columns + ((rows + 1) / 2) * 8 * columns
        } else {
            totalCost = rows * 5 * columns + rows * 4 * columns
        }
    }
    var num = 0
    var row = 0
    var column = 0
    
    while (true) {
        println("")
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        println("")
            
        var entry = readLine()!!.toInt()
        when (entry) {
            0 -> return
            1 -> printCinema(rows, columns, seats)
            2 -> {
                
                while (true) {
                    println("Enter a row number:")
                    row = readLine()!!.toInt()
                    println("Enter a seat number in that row:")
                    column = readLine()!!.toInt()
                    try {
                        if (seats[row - 1][column - 1] == "B") {
                            println("")
                            println("That ticket has already been purchased!")
                            println("")
                        } else {
                            break
                        }
                    } catch (e: RuntimeException){
                        println("")
                        println("Wrong input!")
                        println("")
                    }
                    
                }
                println("")
                seats[row - 1][column - 1] = "B"

                if (rows * columns <= 60) {
                    cost = 10
                    println("Ticket price: ${'$'}$cost")
                } else {
                    if (rows % 2 != 0) {
                        if (row <= (rows - 1) / 2) cost = 10 else cost = 8
                    } else {
                        if (row <= rows / 2) cost = 10 else cost = 8
                    }
                    println("Ticket price: ${'$'}$cost")
                }
                currentCost += cost
                num += 1
                printCinema(rows, columns, seats)
            }
            3 -> {
                println("")
                println("Number of purchased tickets: $num")
                println("Percentage: ${((num * 10000).toBigDecimal() / (rows * columns).toBigDecimal()).setScale(2, RoundingMode.HALF_DOWN) / 100.toBigDecimal()}%")
                println("Current income: ${'$'}$currentCost")
                println("Total income: ${'$'}$totalCost")
            }
        }
    }
}

fun printCinema(rows: Int, columns: Int, seats: Array<Array<String>>) {
    println("Cinema:")
    for (i in 1..columns) {
        if (i == 1) print("  $i ") else print("$i ")
    }
    print("\n")
    for (x in 1..seats.size) {
        print("$x ")
        for (y in 0..seats[x - 1].size - 1) {
            if (y == seats[x - 1].size - 1) println("${seats[x - 1][y]}") else print("${seats[x - 1][y]} ")
        }
    }
}
