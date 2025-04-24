/**
 * 
 */
package String.methods;

import java.util.Arrays;

/**
 * 
 */
public class AllMethodsInOne {

	public static void main(String[] args) {

		String s = "Hello 56 good Morning myname - Hello - is pavan i have my BE in Electronics and Telecommunications";
		String s1 = "Hello good Morning myname is pavan i have completed  my BE in Electronics and Telecommunications";
		String s2 = "Hello good Morning myname is pavan i have my BE in Electronics and Telecommunications";
		/**
		 * 1) Length() --> int it print length of String
		 */

		int length = s.length();

		System.out.println("  (Length method) length of string : " + length);

		/**
		 * 2)charAt(index) -->char it print the value of that index
		 */
		char c = s.charAt(0);

		System.out.println("charAt : " + c);

		/**
		 * 3)codePointAt(index) --> int it print ASCCI value of that index
		 */
		int codePointAt = s.codePointAt(0);

		System.out.println("codePointAt : " + codePointAt);

		/**
		 * 4)codePointBefour(index) -->int it ptint the ASCCI value of befour specific
		 * index
		 */
		int codePointBefour = s.codePointBefore(1);

		System.out.println("codePointBefore : " + codePointBefour);

		/**
		 * compareTo -->int two string to there ubicode/ASCCIi value it return 0 if
		 * string are equal else it return another int value if String not equel
		 */
		System.out.println(s.compareTo(s1));

		String String = s.concat(s1);

		System.out.println(String);

		/**
		 * endsWith-->boolean Checks whether a string ends with the specified
		 * character(s)
		 */

		System.out.println(s.endsWith("s"));

		/**
		 * equals-->boolean Compares two strings. Returns true if the strings are equal,
		 * and false if not
		 * 
		 */

		System.out.println(s1.equals(s2));

		/**
		 * equalsIgnoreCase -->boolean Compares two strings, ignoring Uppercase and
		 * lowercase
		 */

		System.out.println(s1.equalsIgnoreCase(s2));

		/**
		 * getBytes-->byte[] method converts a string into an array of bytes.
		 */
		byte[] p = s1.getBytes();

		System.out.println(Arrays.toString(p));

		/**
		 * indexOf()-->int
		 * 
		 * Returns the position of the first found occurrence of specified characters in
		 * a string
		 */

		System.out.println(s.indexOf('g'));

		/**
		 * hashcode-->int Returns the hash code of a string
		 */
		System.out.println(s.hashCode());

		/**
		 * isEmpty()-->boolean Checks whether a string is empty or not
		 */

		System.out.println(s.isEmpty());

		/**
		 * join --> String Joins one or more strings with a specified separator
		 */

		String[] ss = { "hello", "my", "name", "is", "pavan" };
		System.out.println(String.join("", ss));

		/**
		 * replace() -->String Searches a string for a specified value, and returns a
		 * new string where the specified values are replaced
		 * 
		 */

		System.out.println(s.replace('a', 'd'));

		/**
		 * replaceAll() -->String Replaces each substring of this string that matches
		 * the given regular expression with the given replacement
		 */

		System.out.println(s.replaceAll("Hello", "gh"));

		/**
		 * split() --String[] Splits a string into an array of substring
		 */

		String[] sp = s.split("");

		System.out.println(Arrays.toString(sp));

		/**
		 * split() --> char[] convert string to char Array
		 */

		char[] cc = s.toCharArray();

		System.out.println(Arrays.toString(cc));

		/**
		 * format -->String Returns a formatted string using the specified locale,
		 * format string, and arguments here %s place with string %d place with integer
		 * value
		 * 
		 * 
		 */

		String myString = "Hello %s your Age is %d";
		System.out.println(myString.format(myString, "pavan", 25)); // Hello pavan your Age is 25

		/**
		 * substring--> String Returns a new string which is the substring of a
		 * specified string it avoid last vaue which is you put
		 */

		String pavan = "hello pavan";

		System.out.println(pavan.substring(0, 3));

		/**
		 * contains--> boolean Checks whether a string contains a sequence of characters
		 */

		System.out.println(s.contains("Hello"));

		/**
		 * trim()-->string
		 * 
		 * remove the start and end space of string
		 * 
		 */

		String trim = "     hello    ";
		System.out.println(trim.trim());
		
//		System.out.println(String.valueOf(pavan));

	}
}
