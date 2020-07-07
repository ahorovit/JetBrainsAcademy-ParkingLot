package parking

import java.lang.Exception
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var lotExists = false
    var lot = Lot(0) // Placeholder

    loop@do {

        val input = scanner.nextLine().split(" ")

        try {
            when {
                input[0] == "exit" -> break@loop
                input[0] == "create" -> {
                    val newLot = create(input[1].toInt())
                    if (newLot is Lot) {
                        lot = newLot
                        lotExists = true
                    }
                }
                lotExists -> when (input[0]) {
                    "park" -> park(lot, input[1], input[2])
                    "leave" -> leave(lot, input[1].toInt())
                    "status" -> lot.printStatus()
                }
                else -> println("Sorry, a parking lot has not been created.")

            }
        } catch (e: Exception) {
            println(e.message)
        }
    } while (input[0] != "exit")
}

fun create(spaces: Int): Lot? {
    return if (spaces < 1) {
        println("Lot must have number of spaces > 1")
        null
    } else {
        println("Created a parking lot with $spaces spots.")
        Lot(spaces)
    }
}


fun park(lot: Lot, carId: String, carColor: String) {
    val carToPark = Car(carId, carColor)
    val spaceNumber = lot.park(carToPark)

    if (spaceNumber == null) {
        println("Sorry, the parking lot is full.")
    } else {
        println("${carToPark.color} car parked in spot $spaceNumber.")
    }
}

fun leave(lot: Lot, spaceNumber: Int) {
    val unparkedCar = lot.unpark(spaceNumber)

    if (unparkedCar == null) {
        println("There is no car in spot $spaceNumber.")
    } else {
        println("Spot $spaceNumber is free.")
    }
}