package relationShip;

public class CompositionMobile {

    String mbrand;
    String mmodel;
    double mprice;
    String mcolor;
    int ram;
    int rom;
    int cam;

    
    CompositionBattry battry = new CompositionBattry("Redmi", 1000.0, 4000, 1000);

   
    public CompositionMobile() {
    }

    
    public CompositionMobile(String mbrand, String mmodel, double mprice, String mcolor, int ram, int rom, int cam) {
        this.mbrand = mbrand;
        this.mmodel = mmodel;
        this.mprice = mprice;
        this.mcolor = mcolor;
        this.ram = ram;
        this.rom = rom;
        this.cam = cam;

    }

   
    public void displayMobile() {
        System.out.println("Mobile Brand : " + mbrand);
        System.out.println("Mobile Model : " + mmodel);
        System.out.println("Mobile Price : " + mprice);
        System.out.println("Mobile Color : " + mcolor);
        System.out.println("Mobile RAM   : " + ram + "GB");
        System.out.println("Mobile ROM   : " + rom + "GB");
        System.out.println("Mobile Camera: " + cam + "MP");
        System.out.println();
    }
}
