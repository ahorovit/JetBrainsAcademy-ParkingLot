package parking

import java.lang.Exception

class Space {

    companion object {
        var nextNumber = 1
            get(): Int {
            return field++
        }
    }

    val number: Int

    init {
        this.number = nextNumber
    }


    var state: String = "free"
    var car: Car? = null

    fun parkCar(car: Car) {
        if(state == "taken") {
            throw Exception("space $number is already taken")
        }

        this.car = car
        state = "taken"
    }

    fun freeCar(): Car? {
        val freeCar: Car? = car
        this.car = null
        state = "free"
        return freeCar
    }
}