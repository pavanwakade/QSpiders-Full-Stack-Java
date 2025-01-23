package numberPrograms;

import java.util.Scanner;

public class AreaAndCircumkfaranceOfCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method 
		Scanner circle=new Scanner(System.in);
		System.out.println("enter the Radious of circle");
		int rad=circle.nextInt();
		Double areaofcircle =3.14*(rad*rad);
		System.out.println("Area of circle = "+areaofcircle);
		circle.close();
	}
}
