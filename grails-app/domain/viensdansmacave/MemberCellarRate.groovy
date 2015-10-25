package viensdansmacave

class MemberCellarRate {

    float rate

    static belongsTo = [
            cellar : Cellar,
            member : Member
    ]

    static constraints = {
        cellar unique: ['member']
        rate max: 5.toFloat(), min: 0.toFloat(), nullable: true
    }
}
