package relationShip;

public class CompositionMobileDriver {

    public static void main(String[] args) {
        
        CompositionMobile mobile = new CompositionMobile("Redmi", "Note8", 12000.0, "Black", 4, 64, 48);

        mobile.displayMobile();
        mobile.battry.displayBattry();
    }
}
