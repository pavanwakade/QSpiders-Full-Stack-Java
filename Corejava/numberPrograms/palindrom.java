package numberPrograms;
import java.util.Scanner;
class palindrom
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number");
		int i=sc.nextInt();
		       int last=0;
		       int reversed=0;
		   for (int num=i;num>0;num/=10)	
		{
			last=num%10;
			reversed=reversed*10+last;	
		}
		   if (i==reversed)
		 {
			System.out.println(i+" is palendrom");
		}
		   else
		{
			System.out.println(i+" is not palendrom");
		}
	}
}
