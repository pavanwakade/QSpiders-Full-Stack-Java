package conditionalOperator;

import java.util.Scanner;
class  Condition2
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number");
		int a=sc.nextInt();
		String b=a%2==0 ? "number is even" : "number is odd";
		System.out.println(b);
	}
}
