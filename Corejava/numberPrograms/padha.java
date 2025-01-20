package numberPrograms;
import java.util.Scanner;
class padha
{
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a Number!!!!");
		int Number=sc.nextInt();
		System.out.println();
		for(int i=1;i<=10;i++)
		{
			int padha=Number*i;
			System.out.println("  "+Number+" x "+i+" = "+padha);
		}
	}
}