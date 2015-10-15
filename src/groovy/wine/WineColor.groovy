package wine
/**
 * Created by Romain on 08/10/2015.
 */
enum WineColor {
    RED("rouge"),
    WHITE("blanc"),
    ROSE("rosé"),
    GREY("gris");

    private String color = "";

    WineColor(String color){
        this.color = color;
    }

    public String toString(){
        return color;
    }
}