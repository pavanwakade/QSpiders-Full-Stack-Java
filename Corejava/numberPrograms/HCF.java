package numberPrograms;
import java.util.Scanner;
class HCF
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter num1 ");
            int num1 =sc.nextInt();
			
			System.out.println("Enter num2 ");
            int num2 =sc.nextInt();
			
			int small=num1<num2 ? num1:num2;
			
			while(true)
			{
			   if(num1%small==0 && num2%small==0)
			   {
				 System.out.println("HCF Of "+num1+" and "+num2+" is "+small);
				 break;
			    }
			   small--;
			}
	}
}
