package scanner;
import java.util.Scanner;      //All try
class  Scan2
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your byte number");
		 byte b=sc.nextByte();
				  
		System.out.println("Enter your Short number");
		short s=sc.nextShort();
				
		System.out.println("Enter your int number");
		int I=sc.nextInt();
		System.out.println("Enter your long number");
		long l=sc.nextLong();
		
		System.out.println("Enter your float number");
		float f=sc.nextFloat();
		
		System.out.println("Enter your double number");
		double d=sc.nextDouble();
		
		System.out.println("Enter your char ");
		char c=sc.next().charAt(0);
		
		System.out.println("Enter your boolean ");
		boolean bb=sc.nextBoolean();
	}
}
