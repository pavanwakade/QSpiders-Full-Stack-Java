/*
Above is the standard representation of a chessboard.

This could be imagined as a 2D cartesian plane, with the x axis being represented by the alphabets and y axis by the numbers.

Given coordinates in the form of string, print if that cell is white or black.

Input Format
First line contains a string s.

Output Format
White or Black.

Constraints
|s|=2

Sample Testcase 0
Testcase Input
b2
Testcase Output
Black
Explanation
Self explanatory.
Sample Testcase 1
Testcase Input
a1
Testcase Output
Black
Explanation
We can clearly see a1 is black in the diagram.*/
package problems;

import java.util.*;

class day1 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String input = scanner.next().toLowerCase();
		char col = input.charAt(0);
		char row = input.charAt(1);

		if (input.length() != 2 || col < 'a' || col > 'h' || row < '1' || row > '8') {

			System.out.println("invalid input");
		}

		String BW = "";
		int hight = 8;
		char width = 'h';

		boolean found = false;

		for (int i = 1; i <= hight; i++) {

			for (char jj = 'a'; jj <= width; jj++) {

				BW = jj + "" + "" + i;
				if (input.equals(BW)) {
//					System.out.println(BW);
					found = true;

					break;
				}
			}

			if (found) {
				break;
			}

		}

		System.out.println();

		int colIndex = col - 'a';
		int rowIndex = row - '1';

		if ((colIndex + rowIndex) % 2 == 0) {
			System.out.println("Black");
		} else {
			System.out.println("White");
		}
	}
}
