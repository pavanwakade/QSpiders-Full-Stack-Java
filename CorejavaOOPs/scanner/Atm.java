//if else 
package scanner;

import java.util.Scanner;
class  Atm
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		int password=123;
		System.out.println(" Enter Your Password");
		int pass=sc.nextInt();
		
		
		int Amont=2000;
		int newAmont=0;

		if (pass==password)
		{
		System.out.println(" Enter 1 for Diposit");
		System.out.println(" Enter 2 for Withdraw");
		System.out.println(" Enter 3 for Check Balance");
		int choice=sc.nextInt();
		if (choice==1)
		{
			 System.out.println(" Enter Your Diposit Amount");
			 newAmont=sc.nextInt();
			Amont+=newAmont;
			System.out.println("Your total balance : "+Amont);
		}
		
		else if (choice==2)
		{
			 System.out.println(" Enter Your Withdraw Amount");
			 newAmont=sc.nextInt();
			Amont-=newAmont;
			System.out.println("Your total balance : "+Amont);
		}
		else if (choice==3)
		{
						System.out.println("Your bank balance : "+Amont);
		}
	}
	
	
	else
		{
			System.out.println("invalid password");
			System.out.println("you want to chang the password");
			System.out.println("Y/N");
			char opt=sc.next().charAt(0);
			if (opt=='y')
			{
							System.out.println("Enter your new password : ");
							password= sc.nextInt();
			}
			
		}
}
}