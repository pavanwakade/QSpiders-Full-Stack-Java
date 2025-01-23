package numberPrograms;
import java.util.Scanner;
class  PrimeNoS
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a Number");
		int number=sc.nextInt();;
	    int no=number;
		int deno=2;
		for(;deno<no;deno++)
		{
			if(no%deno==0)	
			{
				break;
			}
		}
		if(no==deno)
		{
			System.out.println(number+" is prime no");
		}
		else
		{
			System.out.println(number+" is not a prime no");
		}
	}
}