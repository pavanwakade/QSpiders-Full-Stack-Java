package Methods;
class MethodChainDriver
{
	public static void main(String[]args)
	{
		MethodChaining c1 =new MethodChaining();
		
		c1.brand="Wildcraft";
		c1.color="Black";
		c1.price=1800;
		c1.capacity=5;
		
		c1.displayproduct().displaybrand().displaycolor().displayprice().displaycapacity();
	}
}