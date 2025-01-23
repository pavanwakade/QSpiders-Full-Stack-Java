package numberPrograms;
import java.util.Scanner;
public class AreaOfTrangle {

	public static void main(String[] args) {
Scanner tr=new Scanner(System.in);
System.out.println("enter the hight");
int hight=tr.nextInt();
System.out.println("Enter the base of trangle");
int base=tr.nextInt();
int areaOfTrangle=(base*hight)/2;
System.out.println("AreaOfTrangle = "+areaOfTrangle);
tr.close();
	}
}
