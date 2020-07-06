package parking

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val lot = Lot(20)

    while(scanner.hasNext()) {

        var input= scanner.nextLine().split(" ")

        if (input[0] == "park") {
            park(lot, input[1], input[2])
        } else if (input[0] == "leave") {
            unpark(lot, input[1].toInt())
        }
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

fun unpark(lot: Lot, spaceNumber: Int) {
    val unparkedCar = lot.unpark(spaceNumber)

    if (unparkedCar == null) {
        println("There is no car in spot $spaceNumber.")
    } else {
        println("Spot $spaceNumber is free.")
    }
}