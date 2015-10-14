package viensdansmacave

class Cellar {
    float rate

    static hasMany = [wines: WineCellar]

    static belongsTo = [
            member : Member
    ]

    static constraints = {
        rate max: 5.toFloat(), min: 0.toFloat(), nullable: true
    }
}
