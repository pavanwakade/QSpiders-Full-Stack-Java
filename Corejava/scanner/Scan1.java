package scanner;
import java.util.Scanner;
class  Scan1
{
	public static void main(String[] args) 
	{
					            String Fname,Mname=" ",Lname=" ";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your First Name ");
		 Fname=sc.next();
		
			System.out.println("You have Middle Name ");
			System.out.println("y/n ");
			char m=sc.next().charAt(0);
			if(m=='y')
		{
						System.out.println("Enter your middle Name ");
			             Mname=sc.next();
			
		}

		System.out.println("You have Last Name ");
			System.out.println("y/n ");
			char n=sc.next().charAt(0);
			
			if(n=='y')
		{
				System.out.println("Enter your Last Name ");
			             	Lname=sc.next();
	}
						 		
						 		System.out.print("  hello "+Fname+" "+Mname+" "+Lname+" ");
		
	}
}
