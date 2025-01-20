package Methods;
class Laptop
{
	String brand;
	String color;
	double price;
	int ram;
	int rom;
	String processor;
	
	public void brand()
	{
		System.out.println("Brand  :"+brand);
	}
	
	public void color()
	{
		System.out.println("Color  :"+color);
	}
	
	public void price()
	{
		System.out.println("Price  :"+price);
	}
	public void ram()
	{
		System.out.println("Ram    :"+ram+"GB");
	}
	
	public void rom()
	{
		System.out.println("Storage:"+rom+"GB");
	}
	
	public void processor()
	{
		System.out.println("Processor:"+processor);
	}
	
	
	
	public void LaptopDisplay()
	{
		System.out.println();
		System.out.println("****Laptops*****");
		brand();
		color();
		price();
		ram();
		rom();
		processor();
	}
	
}