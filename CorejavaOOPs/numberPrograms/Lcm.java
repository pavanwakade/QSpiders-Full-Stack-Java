package numberPrograms;
import java.util.Scanner;

class Lcm {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first number !!");
		int n1 = sc.nextInt();
		System.out.println("Enter first number !!");
		int n2 = sc.nextInt();
		int Larg = n1 > n2 ? n1 : n2;
		while (true) {
			if (Larg % n1 == 0 && Larg % n2 == 0) {
				System.out.println(" LCM of " + n1 + " and " + n2 + " is " + Larg);
				break;
			}
			Larg++;
		}
	}
}
