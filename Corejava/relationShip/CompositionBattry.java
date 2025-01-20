package relationShip;

public class CompositionBattry {

    String brand;
    double price;
    int capacity;
    int chargCycles;

  
    public CompositionBattry() {
    }

 
    public CompositionBattry(String brand, double price, int capacity, int chargCycles) {
        this.brand = brand;
        this.price = price;
        this.capacity = capacity;
        this.chargCycles = chargCycles;
        System.out.println("Battery Created");
    }

    
    public void displayBattry() {
    	System.out.println("----------Battry-----------");
        System.out.println("Brand       : " + brand);
        System.out.println("Price       : " + price);
        System.out.println("Capacity    : " + capacity + " mAh");
        System.out.println("ChargeCycles: " + chargCycles);
    }
}
