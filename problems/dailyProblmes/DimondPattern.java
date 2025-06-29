/**
 * 
 */
package dailyProblmes;

import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 */
public class DimondPattern {

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("enter no");
//		int input = scanner.nextInt();
		int input = 11;

		for (int i = 0; i < input; i++) {

			for (int j = 0; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

		System.out.println();

		
		
		
		for (int i = input; i >= 0; i--) {

			for (int j = 0; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();

		}

		System.out.println();
		
		
		
		

		for (int i = 0; i < input; i++) {

			for (int j = 0; j < input; j++) {

				if (i == 0 || j == 0 || i == input - 1 || j == input - 1 || i == j || i == input / 2 || j == input / 2
						|| j == input - i - 1) {

					System.out.print("* ");
				} else {
					System.out.print("  ");
				}

			}
			System.out.println();

		}

	}

}
