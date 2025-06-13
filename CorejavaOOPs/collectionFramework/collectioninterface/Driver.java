package collectionFramework.collectioninterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Driver {

	public static void main(String[] args) {

		List<Students> students = new ArrayList<>();

		students.add(new Students("pavan", "latur", 67867868));

		students.add(new Students("sp", "latur", 98654));

		students.add(new Students("ram", "heven", 1000));

		Iterator<Students> iterator = students.iterator();
		while (iterator.hasNext()) {

			Students students2 = iterator.next();

			if (students2.name.equals("pavan")) {
				iterator.remove();
				System.out.println("removed");
				
				break;
			} else {
				System.out.println("not found");
			}
		}
		System.out.println("Remaining students:");
		for (Students students2 : students) {
			System.out.println(students2);
		}

	}
}
