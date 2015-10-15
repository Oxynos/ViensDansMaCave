package viensdansmacave

class WineCellar {

    Integer quantity

    static belongsTo = [
            cellar : Cellar,
            wine : Wine
    ]

    static constraints = {
    }

    static belongsTo = [
            cellar : Cellar
    ]
}
