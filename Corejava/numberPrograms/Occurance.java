package numberPrograms;
import java.util.Scanner;

class Occurance 
 {
    public static void main(String[] args) 
	{
        Scanner sc = new Scanner(System.in);
        int count = 0;

        System.out.println("Enter a number");
        int num = sc.nextInt();

        System.out.println("Enter a digit");
        int digit = sc.nextInt();

        while (num != 0) 
		{
            int last = num % 10;
			int temp=last;
			System.out.println(temp);
            if (last == digit) 
			{
                count++;
            }
            num = num / 10; // Reduce the number by removing the last digit
        }

        System.out.println("The digit " + digit + " occurs " + count + " times.");
    }
}