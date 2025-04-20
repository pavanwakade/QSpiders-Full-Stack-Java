/**
 * 
 */
package String.methods;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Contains {

	public static void main(String[] args) {
		List<String> data = Arrays.asList(
				"1,John Doe,john.doe@example.com,29,USA",
				"2,Mane Smith,jane.smith@example.com,34,Canada", 
				"3,Akira Tanaka,akira.t@example.jp,41,Japan",
				"4,Maria Garcia,maria.garcia@example.es,25,Spain", 
				"5,Chen Wei,chen.wei@example.cn,38,China"
				);

		for (String string : data) {

			String[] fields = string.split(",");

			String a = "M";
			
			if (fields[1].contains(a)) {

				System.out.println("id      : "+fields[0] + " ");
				System.out.println("name    : "+fields[1] + " ");
				System.out.println("Email   : "+fields[2] + " ");
				System.out.println("age     : "+fields[3] + " ");
				System.out.println("country : "+fields[4]);
				System.out.println("-----------------------------");
			}
		}
		
		
	}
}
