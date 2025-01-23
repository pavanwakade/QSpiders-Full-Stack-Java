package inheritance.single;

public class SingleInherit {
    String brand;
    String type;
    String model;

    SingleInherit() {

    }
     SingleInherit(String Brand, String Type, String Model) {
        this.brand = Brand;
        this.type = Type;
        this.model = Model;
    }




    public void SingleInheritDisplay()
    {
        System.out.println("Brand :"+brand);
        System.out.println("Type  :"+type);
        System.out.println("Model :"+model);
    }

}