package viensdansmacave

import wine.WineColor

import java.time.LocalDate

class Wine {

    String name
    Integer year
    WineColor color

    static constraints = {
        name blank: false
        year validator: {
            LocalDate date = LocalDate.now()
            if (it > date.year || it < 0) return false
        }
    }
}