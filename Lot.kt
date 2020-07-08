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

    fun printRegByColor(color: String) {
        val matches: MutableList<String> = mutableListOf()

        for (space in spaces) {
            if (space.car?.color?.toLowerCase() == color.toLowerCase()) {
                matches.add(space.car!!.id)
            }
        }

        if (matches.isEmpty()){
            println("No cars with color $color were found.")
        } else {
            println(matches.joinToString(", "))
        }
    }

    fun printSpotByColor(color: String) {

        val matches: MutableList<Int> = mutableListOf()

        for (space in spaces) {
            if (space.car?.color?.toLowerCase() == color.toLowerCase()) {
                matches.add(space.number)
            }
        }

        if (matches.isEmpty()){
            println("No cars with color $color were found.")
        } else {
            println(matches.joinToString(", "))
        }
    }

    fun printSpotByReg(registration: String) {
        for (space in spaces) {
            if (space.car?.id?.toLowerCase() == registration.toLowerCase()) {
                println(space.number)
                return
            }
        }

        println("No cars with registration number $registration were found.")
    }
}