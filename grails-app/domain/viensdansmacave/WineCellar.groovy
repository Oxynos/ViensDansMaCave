package viensdansmacave

class WineCellar {

    Wine wine
    Cellar cellar
    Integer quantity

    static constraints = {
    }

    static belongsTo = [
            cellar : Cellar
    ]
}
