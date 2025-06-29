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
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("enter no");
//		int input = scanner.nextInt();
		int input = 10;

		for (int i = 0; i < input; i++) {
			
			for (int j = 0; j < input; j++) {
				
				if (i==0 || j==0 || i==input-1 || j==input-1 || i==j || i==input/2 || ) {
					
					System.out.print("* ");
				}
				else { 
					System.out.print("  ");
				}
				 
			}
			System.out.println();
			
		}

	}

}
