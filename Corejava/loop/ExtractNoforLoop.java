package loop;
import java.util.Scanner;
class ExtractNoforLoop
{
	public static void main(String[] args) 
	{	
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter  Number!!!");
		int count=0;
	    int num=sc.nextInt();
		for (int i=num;i>0 ; i/=10)
		{
			count++;
		}
		System.out.println("Count of Enterd Number is : "+count);
	}
}
