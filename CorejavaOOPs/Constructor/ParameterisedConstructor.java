package Constructor;
class ParameterisedConstructor         //SmartWatch
{
	String Product="SmartWatch";
	String brand;
	double price;
	String color;


	ParameterisedConstructor()
	{
		
	}

	ParameterisedConstructor(String brand,String color,double price)
	{
		this.brand=brand;
		this.price=price;
		this.color=color;
	}
	public void displayProduct()
	{
		System.out.println("Product :"+Product);
		System.out.println("Brand   :"+brand);
		System.out.println("Price   :"+price);
		System.out.println("Color  :"+color) ;
	}
}
