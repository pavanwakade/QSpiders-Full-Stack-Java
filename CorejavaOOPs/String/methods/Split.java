/**
 * 
 */
package String.methods;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Split {

	public static void main(String[] args) {

		// first way to use

		String s = "hello pavan";

		String[] a = new String[s.length()];

		a = s.split("");

//		String ssString=new String();

		System.out.println(Arrays.toString(a));
		System.out.println();

		// second way to use

		List<String> data = Arrays.asList("1,John Doe,john.doe@example.com,29,USA",
				"2,Jane Smith,jane.smith@example.com,34,Canada", "3,Akira Tanaka,akira.t@example.jp,41,Japan",
				"4,Maria Garcia,maria.garcia@example.es,25,Spain", "5,Chen Wei,chen.wei@example.cn,38,China");

		for (String line : data) {
			String[] fields = line.split(",");
			String id = fields[0];
			String name = fields[1];
			String email = fields[2];
			String age = fields[3];
			String country = fields[4];

			System.out.println(
					"id: " + id + ", Name: " + name + ", Email: " + email + ", Age: " + age + ", Country: " + country);
		}
		
		System.out.println();

		 
	}
}