package loopWhile;

import java.util.Scanner;

class leapFor
 {
    public static void main(String[] args) 
		{
        Scanner sc = new Scanner(System.in);
        
        for (int i = 1; i <= 2000;   i++)
			{
			if (i%2==0)
			{
				System.out.println(i);
			}
        }
    }
}
