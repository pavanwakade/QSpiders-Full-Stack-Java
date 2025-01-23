package numberPrograms;
import java.util.Scanner;

public class NeonNo
{
    public static void main(String [] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a Number!!!");
        int num=sc.nextInt();
		
        int Square=num*num;
        System.out.println("Square :"+Square);

        int sum=0;
        while (Square>0)
        {
            sum = sum + (Square % 10);
            Square /= 10;
        }
        System.out.println("sumSQR :"+sum);

        if (num==sum)
        {
            System.out.println("   "+num+" is a Neon Number");
        }
        else
        {
            System.out.println("   "+num+" is Not Neon Number");
        }
    }
}
