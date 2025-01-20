package numberPrograms;

import java.util.Scanner;

public class GrossSalaryofanEmployee {

	public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
System.out.println("Enter your basic salary amount");
int Bsal=sc.nextInt();

System.out.println("enter your house rent allowanse");
int houserent=sc.nextInt();

int totalsal=(Bsal+houserent);
System.out.println("total salaryn = "+totalsal);

	}

}
