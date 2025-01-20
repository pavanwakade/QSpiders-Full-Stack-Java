///factorial without loop
package Methods.recurtion;

import java.util.Scanner;
class Factorial
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a Number!!!");
		System.out.println(fact(sc.nextInt()));
	}
	
	public static int fact(int num)
	{
		if(num==0)
		{
			return 1;
		}
		return num*fact(num-1);
	}
}