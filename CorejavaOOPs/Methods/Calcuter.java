package Methods;
public class Calcuter
{

	//Addition
	public static double add(double a,double b)
	{
		return a+b;
	}
	
	//Substraction
	public static double sub(double a,double b)
	{
		return a-b;
	}
	
	//multiplication
	public static double mul(double a,double b)
	{
		return a*b;
	}
	
	//division
	public static double div(double a,double b)
	{
		return a/b;
	}
	
	public static void main(String[] args) 
	{
        
		System.out.println("Addition : "+add(5,5));
		System.out.println("Substraction : "+sub(5,8));
		System.out.println("multiplication : "+mul(5,8));
		System.out.println("division : "+div(5,7));
       
    }
}  
	
