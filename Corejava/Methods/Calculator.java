package Methods;
import java.util.Scanner;
public class Calculator
{

public static void main(String[] args) 
	{
		calculator();
	}

       
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
	
	
	public static void calculator()
	{
		System.out.println("******** Wellcome To Calculator ********");
		Scanner sc=new Scanner(System.in);
		System.out.println("    +");
		System.out.println("    -");
		System.out.println("    *");
		System.out.println("    /");
        char choice=sc.next().charAt(0);
		switch(choice)
		{
			case '+':
			{
				System.out.println("Addition!");
				System.out.println("Enter a Number!");
				int a=sc.nextInt();
				System.out.println("Enter a Number!");
				int b=sc.nextInt();
				System.out.println("Addition : "+a+" + "+b+" = "+add(a,b));
				break;
			}
			
			
			case '-':
			{
				System.out.println("Substraction!");
				System.out.println("Enter a Number!");
				int a=sc.nextInt();
				System.out.println("Enter a Number!");
				int b=sc.nextInt();
				System.out.println("Substraction : "+a+" - "+b+" = "+sub(a,b));
				break;
			}
			
			case '*':
			{
				System.out.println("Multiplication!");
				System.out.println("Enter a Number!");
				int a=sc.nextInt();
				System.out.println("Enter a Number!");
				int b=sc.nextInt();
				System.out.println("Substraction : "+a+" * "+b+" = "+mul(a,b));
				break;
			}
			
			
			case '/':
			{
				System.out.println("Division!");
				System.out.println("Enter a Number!");
				int a=sc.nextInt();
				System.out.println("Enter a Number!");
				int b=sc.nextInt();
				System.out.println("Substraction : "+a+" / "+b+" = "+div(a,b));
				break;
			}
			
			default :
			{
				System.out.println("invalid input");
				break;
			}
		}
	}
	
	
}
 
	
