package numberPrograms;
import java.util.Scanner;
class ReverseNoPalindrome
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("   Enter Number");
        int num =sc.nextInt();
		reverse(num);
	}
	
	
	public static int reverse(int i)
	{
		System.out.println("   orignal Number :"+i);
		int rev=0;
		int numb=i;
	    for (;i>0;i/=10)
	    {
		  rev=rev*10+(i%10);
	    }
		System.out.println("   reverse Number :"+rev);
		
		System.out.println("   Do you want to check if it's a palindrome? (y/n)");
		 Scanner sc=new Scanner(System.in);
		char check=sc.next().charAt(0);
		
		if(check=='y')
		{
			if(checkpalindrom(numb,rev))
			{
				 System.out.println("   "+numb+" is palindrome Number!!!");
			}
			else
			{
				System.out.println("   "+numb+" is not palindrome Number!!!");
			}
		}
		return rev;
    }
	
	
	
	public static boolean checkpalindrom(int num,int rev)
	{
		return num==rev;
	}
}
