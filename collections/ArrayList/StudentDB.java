package ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class StudentDB {

	public static void main(String[] args) {
		ArrayList<Student> al = new ArrayList<>();
		al.add(new Student("pavan ", 65.9, 2024));
		al.add(new Student("Shashi", 95.9, 2023));
		al.add(new Student("kedar ", 85.9, 2022));
		al.add(new Student("Nvnath", 69.9, 2024));
		al.add(new Student("Shubhm", 70.9, 2021));
		al.add(new Student("Ranjit", 80, 2023));
		al.add(new Student("vishal", 65.9, 2024));
		al.add(new Student("Yogesh", 70.9, 2024));
		al.add(new Student("Sandip", 99.9, 2022));
		al.add(new Student("Akash ", 70.9, 2024));
		al.add(new Student("ganesh", 60.9, 2025));
		al.add(new Student("Sonali", 85.9, 2024));
//		printDB(al);
		removeByYOP(al, 2024);
		removeByPercentage(al, 80);
		Collections.sort(al);
		printDB(al);

	}

	public static void removeByYOP(ArrayList<Student> al, int YOP) {
		Iterator<Student> i = al.iterator();
		while (i.hasNext()) {
			Student ref = i.next();
			if (ref.yop < YOP - 2) {
				i.remove();

			}

		}
	}

	public static void removeByPercentage(ArrayList<Student> al, int per) {
		Iterator<Student> i = al.iterator();
		while (i.hasNext()) {
			Student ref = i.next();
			if (ref.per < per) {
				i.remove();

			}

		}
	}

	public static void printDB(ArrayList<Student> al) {
		for (Student student : al) {
			System.out.println(student);
		}
	}

}
