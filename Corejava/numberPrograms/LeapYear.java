package numberPrograms;
import java.util.Scanner;
class  LeapYear
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println(" Enter Year");
		int year=sc.nextInt();
		if (year%4==0 )
		{
			System.out.println("leap year");
		}
		else if (year<0)
		{
						System.out.println(" invalid year");
		}
		else{
									System.out.println(" NO leap year");
		}
	}
}
