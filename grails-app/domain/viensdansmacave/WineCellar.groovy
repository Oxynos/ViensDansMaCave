package viensdansmacave

class WineCellar {

    Integer quantity

    static belongsTo = [
            cellar : Cellar,
            wine : Wine
    ]

    static constraints = {
        quantity min: 1
        cellar unique: ['wine']
    }
}
