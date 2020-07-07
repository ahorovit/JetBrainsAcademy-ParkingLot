package parking

import java.lang.Exception

class Lot (numberSpaces: Int){
    val spaces: Array<Space>
    val numberSpaces: Int
    var freeSpaceCount: Int

    init {
        if (numberSpaces < 0) {
            throw Exception("invalid numberSpace value: $numberSpaces")
        }

        this.numberSpaces = numberSpaces
        this.freeSpaceCount = numberSpaces
        this.spaces = Array(numberSpaces) { Space() }
        Space.reset()
    }

    fun park(car: Car): Int? {
        for (space in spaces) {
            if (space.state == "free") {
                space.parkCar(car)
                freeSpaceCount--
                return space.number
            }
        }

        return null
    }

    fun unpark(spaceNumber: Int): Car? {

        val result = spaces[spaceNumber - 1].freeCar()

        if(result is Car) {
            freeSpaceCount++
        }

        return result
    }

    fun printStatus() {
        if (freeSpaceCount == numberSpaces) {
            println("Parking lot is empty.")
            return
        }

        for (space in spaces) {
            if (space.state == "free") {
                continue
            }

            println("${space.number} ${space.car!!.id} ${space.car!!.color}")
        }
    }
}