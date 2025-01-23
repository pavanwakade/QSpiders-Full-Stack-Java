package numberPrograms;

import java.util.Scanner;

class pal 
{
    public static void main(String[] args) 
	{
        Scanner sc=new Scanner(System.in);
		
        System.out.println("Enter a Number!!!");
       int ip=sc.nextInt();
	    
	   if (palindrome(ip))
	   {
		   System.out.println(ip+" is palindrom");
	   }
		 else{
			 System.out.println(ip+" is not palindrome");
		 }  
	}
	public static boolean palindrome(int number)
	{
		int num=number;
		int rev=0;
       for (int i=num;i!=0;i/=10) 
	   {
         rev=rev*10+(i%10);
       } 
        return number==rev;
    }
}