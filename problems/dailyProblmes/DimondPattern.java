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

//		
//		* 
//		* * 
//		* * * 
//		* * * * 
//		* * * * * 
//		* * * * * * 
//		* * * * * * * 
//		* * * * * * * * 
//		* * * * * * * * * 
//		* * * * * * * * * * 
//		* * * * * * * * * * *
//		
//		
//
		for (int i = 0; i < input; i++) {

			for (int j = 0; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

		System.out.println();
		
		
		
		

//		
//		* * * * * * * * * * * * 
//		* * * * * * * * * * * 
//		* * * * * * * * * * 
//		* * * * * * * * * 
//		* * * * * * * * 
//		* * * * * * * 
//		* * * * * * 
//		* * * * * 
//		* * * * 
//		* * * 
//		* * 
//		* 

		for (int i = input; i > 0; i--) {

			for (int j = 0; j < i; j++) {
				System.out.print("* ");
			}
			System.out.println();

		}

		System.out.println();
		
		
		
		
		
		
		
		

//          * 
//         * * 
//        * * * 
//       * * * * 
//      * * * * * 
//     * * * * * * 
//    * * * * * * * 
//   * * * * * * * * 
//  * * * * * * * * * 
// * * * * * * * * * * 
//* * * * * * * * * * * 

		for (int i = 0; i <= input; i++) {

			for (int j = 0; j <= input - i; j++) {
				System.out.print(" ");
			}

			for (int j = 0; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

		System.out.println();
		
		
		
		
		
		
//          * 
//         * * 
//        * * * 
//       * * * * 
//      * * * * * 
//     * * * * * * 
//    * * * * * * * 
//   * * * * * * * * 
//  * * * * * * * * * 
// * * * * * * * * * * 
//* * * * * * * * * * * 
// * * * * * * * * * * 
//  * * * * * * * * * 
//   * * * * * * * * 
//    * * * * * * * 
//     * * * * * * 
//      * * * * * 
//       * * * * 
//        * * * 
//         * * 
//          * 
		
		
		
		

		// Upper half (including middle)
        for (int i = 0; i <= input; i++) {
            for (int j = 0; j <= input - i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < i; k++) {
                System.out.print("* ");
            }

            System.out.println();
        }

        // Lower half (exclude middle)
        for (int i = input - 1; i >= 1; i--) {
            for (int sp = 0; sp <= input - i; sp++) {
                System.out.print(" ");
            }

            for (int j = 0; j < i; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }

		System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		

//		
//		* * * * * * * * * * * 
//		* *       *       * * 
//		*   *     *     *   * 
//		*     *   *   *     * 
//		*       * * *       * 
//		* * * * * * * * * * * 
//		*       * * *       * 
//		*     *   *   *     * 
//		*   *     *     *   * 
//		* *       *       * * 
//		* * * * * * * * * * * 

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
