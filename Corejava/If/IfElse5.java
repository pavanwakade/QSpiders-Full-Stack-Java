package If;
import java.util.Scanner;
class  IfElse5       //number is positive or negative
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number");
		int num=sc.nextInt();
		if(num>=0)
		{
		System.out.println(num+"  is Possitive");
		}
		else{
					System.out.println(num+"  is Negetive");
		}
	}
}
