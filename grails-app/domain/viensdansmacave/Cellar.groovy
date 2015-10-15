package viensdansmacave

class Cellar {
    float rate

    static belongsTo = [
            member : Member
    ]

    static hasMany = [
            wineCellars : WineCellar
    ]

    static constraints = {
        rate max: 5.toFloat(), min: 0.toFloat(), nullable: true
    }
}
