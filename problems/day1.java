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

//		String BW = "";
//		int hight = 8;
//		int width = 8;
//
//		for (int i = 1; i <= hight; i++) {
//
//			for (int j = 1; j <= width; j++) {
////				System.out.println(j);
//				if (j % 2 == 0) {
//
//					BW = i + " " + " " + j;
//					
//					System.out.println(" " + BW);
//
//				} else {
//					System.out.print(" white");
//				}
//
//			}
//			System.out.println();
//
//		}
		
		for (char cc = 'a';  cc<= 'z'; cc++) {
			System.out.println(cc);
		}
	}
}
