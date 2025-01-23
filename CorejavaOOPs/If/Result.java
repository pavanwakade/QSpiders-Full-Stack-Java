package If;
import java.util.Scanner;
class  Result
{
	public static void main(String[] args) 
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Percentage ");
		float result=sc.nextFloat();
		
		if (result>0 && result<35)
		{
			System.out.println("Your Are Fail");
		}
		
	else	if (result==35)
		{
			System.out.println("You Are Just Pass!!!");
		}
		
		else if (result>=35 && result<60)
		{
			System.out.println("Your Are Pass in Second Distringtion !!!!");
		}
		else if (result>=60 && result<75)
		{
			System.out.println("You Are Pass in Firest Distriction !!!!");
		}
		else	if (result>=75 && result<100)
			{
			System.out.println("You Are Pass in Distrinction!!!!");
			}
			else{
				System.out.println("Invalid Precentage!!!");
			}
			
	}
}
