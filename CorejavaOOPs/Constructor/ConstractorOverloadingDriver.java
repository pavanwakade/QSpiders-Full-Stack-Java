package Constructor;
class ConstractorOverloadingDriver
{
	ConstractorOverloadingDriver()
	{

	}
	
	public static void main(String[]args)
	{
		ConstractorOverloading ov1=new ConstractorOverloading("Pavan","p@123");
		ov1.displayForm();
		
		ConstractorOverloading ov2=new ConstractorOverloading("Pavan","p@123","10/08/1999",8317277608l);
		ov2.displayForm();
	}
}