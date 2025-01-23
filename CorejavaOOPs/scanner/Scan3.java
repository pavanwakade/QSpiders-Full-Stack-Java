package scanner;
import java.util.Scanner;      //introduction
class  Scan3
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your Full name");
		String name=sc.nextLine();
		
		System.out.println("enter your city");
		String city=sc.nextLine();
		
		System.out.println("enter your Eduction");
		String edu=sc.nextLine();
		
		System.out.println("enter your Project");
		String pro=sc.nextLine();

		System.out.println("enter your Experiance year");
		int exp=sc.nextInt();
		
		System.out.println("enter your Mobile no");
		long mo=sc.nextLong();
		
		System.out.println("enter your Email");
		String email=sc.next();
		
		System.out.println();
		
		System.out.println("hello  "+ name);
		System.out.println("your city is : "+city);
		System.out.println("your edu is : "+edu);
		System.out.println("your Project is : "+pro);
		System.out.println("your Experiance is : "+exp);
		System.out.println("your Email is : "+email);
		System.out.println("your Mobile no is : "+mo);
		
	}
}