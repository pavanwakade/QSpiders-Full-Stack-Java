package numberPrograms;
import java.util.Scanner;
class DuctNO
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Number");
             int no =sc.nextInt();
			 int last=0;
			 for(;no!=0;no/=10)
			 {
				 last=no%10;
			 }
				 if(last==0)
				 {
					 System.out.println("Number is duct Number");
				 }
				 else  
				 {
					 System.out.println("not Duct number");
				 }
	}
}
