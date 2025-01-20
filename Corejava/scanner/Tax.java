package scanner;
import java.util.Scanner;
class  Tax
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
	     System.out.println("Enter your Salary");
		int sal=sc.nextInt();
		
		if (sal>0 && sal<300000)
		{
		System.out.println("Happy No need to pay tax ");
		}
		else if (sal>=300001 && sal<=700000)
		{
			sal=sal-(sal*5/100);
		}
		else if (sal>=700001 && sal<=1000000)
		{
	       sal=sal-(sal*10/100);
		}
		else if (sal>=1000001 && sal<=1200000)
		{
	       sal=sal-(sal*15/100);
		}
		else if (sal>=1200001 && sal<=1500000)
		{
	       sal=sal-(sal*20/100);
		}
		else if (sal>=1500001)
		{
	       sal=sal-(sal*30/100);
		}
		else
		{
		System.out.println(" invalid Salary !!!");
		}
			
		if (sal>0)
		{
		System.out.println(" Your Annual Salary After Tax : "+sal);
		System.out.println(" Your per Month Salary After Tax : "+sal/12);
		}
	}
}