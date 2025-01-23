package Methods;
class MethodChaining
{
	String product="Bag";
	String brand;
	String color;
	double price;
	int capacity;
	
	public MethodChaining displayproduct()
	{
		System.out.println("product :"+product);
		return this;
	}
	public MethodChaining displaycolor()
	{
		System.out.println("Color :"+color);
		return this;
	}
	
	public MethodChaining displayprice()
	{
		System.out.println("Price :"+price);
		return this;
	}
	
	public MethodChaining displaycapacity()
	{
		System.out.println("capacity :"+capacity);
		return this;
	}
	
	public MethodChaining displaybrand()
	{
		System.out.println("brand :"+brand);
		return this;
	}
}