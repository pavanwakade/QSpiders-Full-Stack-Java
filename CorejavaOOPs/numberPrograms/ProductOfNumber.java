package numberPrograms;
import java.util.Scanner;

class ProductOfNumber 
{
    public static void main(String[] args) 
	{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Number");
        int num = sc.nextInt();
        int sum = 0;
        int product = 1;

        while (num > 0) 
		{
            int digit = num % 10;
            sum += digit;
            product *= digit;
            num /= 10;
        }

        System.out.println(sum);
        System.out.println(product);

        if (sum == product) {
            System.out.println("Good No");
        } else {
            System.out.println("Bad No");
        }
    }
}
