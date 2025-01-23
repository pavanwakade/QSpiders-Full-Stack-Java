package numberPrograms;
import java.util.Scanner;
class  BuzzNo
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no");
		int no=sc.nextInt();
	      if(no%7==0 || no%10==7)
		  {
			  System.out.println(no+" is Buzz No");
		  }
		  else
		  {
			  System.out.println(no+" is not BuzzNo");
		  }
		
	}
}

