package Methods;
class LaptopDriver
{
	public static void main(String[] args)
	{
		Laptop lp=new Laptop();
		lp.brand="HP";
		lp.color="Silver";
		lp.price=120000;
		lp.ram=16;
		lp.rom=512;
		lp.processor="i7";
		
		lp.LaptopDisplay();
	}
}