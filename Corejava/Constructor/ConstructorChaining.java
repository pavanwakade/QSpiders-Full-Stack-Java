package Constructor;
class ConstructorChaining
{
	String product ="Android Tv";
	double price;
	String color;
	String size;
	int ram;
	int rom;
	
	ConstructorChaining(){}
	
	ConstructorChaining(double price,String color)
	{
		this.price=price;
		this.color=color;
	}
	
	ConstructorChaining(double price,String color,String size,int ram)
	{
		this(price,color);
		this.size=size;
		this.ram=ram;
	}
	
	ConstructorChaining(double price,String color,String size,int ram,int rom)
	{
		this(price,color,size, ram);
	
		this.rom=rom;
	}
	
	
	public void ConstructorChainingDisplay()
	{
		System.out.println("Product: " + product);
		System.out.println("Price  : " + price);
		System.out.println("Size   : " + size);
		System.out.println("Color  : " + color);
		System.out.println("Ram    : " + ram + " GB");
		System.out.println("Storage: " + rom + " GB");
	}
}