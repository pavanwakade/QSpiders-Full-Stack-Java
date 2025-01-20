package numberPrograms;

import java.util.Scanner;

//Converting Temperature Celsius into Fahrenheit
public class ConvertingTemperatureCelsiusintoFahrenheit {

	public static void main(String[] args) {
		float c,f;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the temprecture in celsias");
		c=sc.nextInt();
		f=(c*9/5)+32;
System.out.println("Fahrenheit is "+f);
	}

}
