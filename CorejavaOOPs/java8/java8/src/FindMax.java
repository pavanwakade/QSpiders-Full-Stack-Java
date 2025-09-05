/**
 * 
 */
package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 */
public class FindMax {

	public static void main(String[] args) {

		List<Integer> number = Arrays.asList(1, 2, 35, 6, 8, 9);
		System.out.println(number.stream().filter(x -> x % 2 == 0).count());

		int max= number.stream().max(Comparator.naturalOrder()).get();
		System.out.println("max  :"+max);

		int[] i = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Arrays.stream(i).filter(x -> x % 2 == 0).forEach(System.out::println);
		
		
		
		
		String ss="madam";
		
		boolean ispalindrome=new StringBuffer(ss).reverse().toString().equals(ss);
		
		String checkpalindrome=ispalindrome ? "palindrome" : "not a palindrome";
		
		 System.out.println(checkpalindrome);		
		
		
	}

}
