/**
 * 
 */
package dailyProblmes;

import java.util.Scanner;

/**
 * 
 */
public class DimondPattern {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter no");
		int input = scanner.nextInt();

		for (int i = 0; i < input; i++) {
			for (int j = 0; j < i-1; j++) {
				System.out.print(" ");
			}
				
			for (int j = 0; j < input; j++) {
				System.out.print("* ");
				
			}
			System.out.println();
			}
			
			
	}

}
