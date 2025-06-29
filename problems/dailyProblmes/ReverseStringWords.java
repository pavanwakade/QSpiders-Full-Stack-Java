/**
 * 
 */
package dailyProblmes;

import java.util.Arrays;

/**
 * simple inpiut String str="i Love Java i like java"; expected op -> i evoL
 * avaJ i ekil avaJ
 */
public class ReverseStringWords {

	public static void main(String[] args) {

		String str = "i Love Java i like java";

		ReverseWords(str);

	}

	public static String ReverseWords(String str) {

		String s = "";

		String[] strArr = str.split(" ");
		for (String word : strArr) {

			String reversedwordString = "";

			String[] c = word.split("");

			for (int i = c.length - 1; i >= 0; i--) {

				reversedwordString += c[i];
			}
			s += reversedwordString + " ";

//			System.out.println(s);
		}
		System.out.println(s);
		return s;
	}
}
