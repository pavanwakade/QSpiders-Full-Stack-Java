package numberPrograms;
import java.util.Scanner;
public class SimpleInterest {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter you amount");
		 float amount=sc.nextFloat();

		System.out.println("Enter you interest Rate");
		float interest=sc.nextFloat();
		
		System.out.println("Enter you time period in yers");
		float totalTime=sc.nextFloat();
		
		double totalInterest=(amount*interest*totalTime)/100;
		System.out.println("simple intersest = "+totalInterest);
		sc.close();

	}

}
