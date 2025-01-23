package numberPrograms;
import java.util.Scanner;

class PrimCount {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter enter Number");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int count = 0;
		while (num1 <= num2) {
			for (int deno = 0; deno < num1; deno++) {
				if (num1 % deno == 0) {
					break;
				}

				if (deno == num1) {
					System.out.println(num1 + " is prime no");
					count++;
				}
			}
			num1++;
		}
		System.out.println(count);
	}
}
