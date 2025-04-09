package collection.collectioninterface;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {

		List<Students> students = new ArrayList<>();

		students.add(new Students("pavan", "latur", 67867868));

		students.add(new Students("sp", "latur", 98654));

		for (Students students2 : students) {

			System.out.println(students2);
		}
	}
}
