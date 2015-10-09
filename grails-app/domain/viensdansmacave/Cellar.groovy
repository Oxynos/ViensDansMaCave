package viensdansmacave

class Cellar {
    float rate

    static constraints = {
        rate max: 5.toFloat(), min: 1.toFloat(), nullable: true
    }
}
