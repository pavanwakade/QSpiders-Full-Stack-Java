package pattern;
import java.util.Scanner;
class Trangle{
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);

		int in=sc.nextInt();

		for (int i=0;i<=in ;i++ ) 
		{
			for (int j=1;j<=i ;j++ ) 
			{
				System.out.print("* ");
              	
           	}
           	System.out.println();
		}

// System.out.println(" ");

		for (int i=0;i<=in ;i++ ) 
		{
			for (int j=i;j<=in ;j++ ) 
			{
				System.out.print("* ");
              	
           	}
           	System.out.println();
		}
	}
}