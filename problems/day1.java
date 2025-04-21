import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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
		
		boolean found=false;

		for (int i = 1; i <= hight; i++) {

			for (char jj = 'a'; jj <= width; jj++) {

				BW = jj + "" + "" + i;
				if (input.equals(BW)) {
//					System.out.println(BW);
					found=true;

					break;
				}
			}
			
			if (found) {
				break;
			}

		}

		 int colIndex = col - 'a';       // e.g. 'a' = 0, 'b' = 1, ...
	        int rowIndex = row - '1';       // e.g. '1' = 0, '2' = 1, ...

	        if ((colIndex + rowIndex) % 2 == 0) {
	            System.out.println("Black");
	        } else {
	            System.out.println("White");
	        }
	    }
}
