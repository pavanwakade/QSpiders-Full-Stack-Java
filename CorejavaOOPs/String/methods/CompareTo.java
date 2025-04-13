/**
 * 
 */
package String.methods;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class CompareTo {

	public static void main(String[] args) {

		String s = "pavan wakade";

		List<String> data = Arrays.asList("1,John Doe,john.doe@example.com,29,USA",
				"2,Jane Smith,jane.smith@example.com,34,Canada", "3,Akira Tanaka,akira.t@example.jp,41,Japan",
				"4,Maria Garcia,maria.garcia@example.es,25,Spain", "5,Chen Wei,chen.wei@example.cn,38,China");

		for (String string : data) {

			String[] fields = string.split(",");

			if (fields[0].contains("3")) {
				
				System.out.println(fields[0]);
			}
		}
//		System.out.println(data.contains("ravan"));

		String s1 = "pavan wakade";

		String s2 = "name is pavan";

//		System.out.println(s.compareTo(s1)); // return type is int
		// it compare by every charactor by sequence
//		if all sequenced charactor  are equal thhan return 0 or return diff of ASCCI values 
	}
}
