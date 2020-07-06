package parking

class Lot(val numberSpaces: Int) {
    val spaces = Array(numberSpaces) { Space()}

    fun park(car: Car): Int? {
        for (space in spaces) {
            if (space.state == "free") {
                space.parkCar(car)
                return space.number
            }
        }

        return null
    }

    fun unpark(spaceNumber: Int): Car? {
        return spaces[spaceNumber - 1].freeCar()
    }
}