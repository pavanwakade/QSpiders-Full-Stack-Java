package Methods;
class ThisBike
{
	static String vehical ="Twoweeler";
	static String brand;
	static String name;
	
	String color;
	double price;
	int cc;
	int avg;
	
	static{
		System.out.println();
		System.out.println("********* Well-come To ShowRoom *********");
		System.out.println();
	}
	public void displayBike(String brand,String name,String color,	double price,int cc,int avg)
	{
		brand=brand;
		name=name;
		this.color=color;
		this.price=price;
		this.cc=cc;
		this.avg=avg;
		System.out.println("vehical Type :"+vehical);
		System.out.println("Brand :"+brand);
		System.out.println("Name  :"+name);
	    System.out.println("color :"+color);
		System.out.println("Price :"+price);
		System.out.println("cc    :"+cc);
		System.out.println("avg   :"+avg);
		System.out.println("-----------------------------");
	}
	
}