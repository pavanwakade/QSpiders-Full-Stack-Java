package numberPrograms;
import java.util.Scanner;
class DuckNumber
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your num !!!!");
		int num1=sc.nextInt();
		int num=num1;
		int last=0;
		//int position=1;
		while (num!=0)
		{
			 last=num%10;
			 if (last==0)
			{
				// position++;
				 break;
			}
			  num/=10;
		}
		if (num==0)  
		{
			 System.out.println(num1+" : is not Duck Number");
		}
		else
		{
			System.out.println(num1+" :  is a Duck Number");
			//System.out.println("position of Zero is "+(position+1));
		}
	}
}
