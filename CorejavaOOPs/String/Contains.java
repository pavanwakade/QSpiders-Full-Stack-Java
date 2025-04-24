/**
 * 
 */
package String;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class Contains {

	public static void main(String[] args) {
		List<Employee> emp = new ArrayList<Employee>();
		emp.add(new Employee(1, "pavan", "wakade"));
		emp.add(new Employee(2, "rahul", "sharma"));
		emp.add(new Employee(3, "anita", "deshmukh"));
		emp.add(new Employee(4, "suresh", "patil"));
		emp.add(new Employee(5, "meena", "jadhav"));
		emp.add(new Employee(6, "arjun", "rao"));
		emp.add(new Employee(7, "priya", "kale"));
		emp.add(new Employee(8, "vikas", "joshi"));

		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		for (Employee employee : emp) {

			if (employee.getFname().contains(name)) {
				System.out.println(employee);
			}
		}

	}

}
