package pattern;

import java.util.Scanner;

public class DiamondPattern {
    public static void main(String[] args) {
    	System.out.println("enter number");
       Scanner sc= new Scanner(System.in);

        int in=sc.nextInt();

        for (int i=1;i<=in ;i++ ) 
        {
            for (int sp=1;sp<=(in-i);sp++ ) {
                System.out.print(" ");
            }
            for (int j=1;j<=i ;j++ ) 
            {
                System.out.print("* ");
                
            }
                System.out.println();
        }
 

       for (int i=in;i>=0 ;i-- ) {
            for (int sp=1;sp<=(in-i) ;sp++ ) {
               System.out.print(" ");
             }
            for (int j=1;j<=i ;j++ ) {
               System.out.print("* ");
             }

    
          System.out.println();
        }         
    }
}
