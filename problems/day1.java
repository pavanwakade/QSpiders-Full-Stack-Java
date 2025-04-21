import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class day1 {

	public static void main(String[] args) {
		int hight = 8;
		int width = 8;

		for (int i = 1; i <= hight; i++) {
			

			for (int j = 1; j <= width; j++) {
//				System.out.println(j);
				if (j % 2 == 0) {
					System.out.print(" b"+j);

				}
				else {
					System.out.print(" w"+j);
				}

			}
			System.out.println();

		}
	}
}

//    public static swape(String black,String white){
//        String temp=black;
//        black=white;
//        white=temp;
//    }
