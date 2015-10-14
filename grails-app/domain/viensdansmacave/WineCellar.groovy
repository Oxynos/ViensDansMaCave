package viensdansmacave

class WineCellar {

    Wine wine
    Cellar cellar
    Integer quantity

    static constraints = {
        quantity min: 1
        cellar unique: ['wine']
    }
}
