/**
 * 
 */
package StudentManagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * 
 */
public class StudentsCRUD implements StudentsInterface {
	List<Students> students = new LinkedList<>();
	Scanner sc = new Scanner(System.in);

	@Override
	public void addStudent() {

		System.out.println("Enter Roll no");
		int rollNo = sc.nextInt();
		sc.nextLine();

		System.out.println("ENter Name");
		String name = sc.nextLine();

		System.out.println("Enter Marks");
		double marks = sc.nextDouble();

		students.add(new Students(rollNo, name, marks));
		System.out.println("Student add sucessfully");
	}

	@Override
	public void viewStudents() {
		if (students.isEmpty()) {
			System.out.println("students not added");
		} else {
			for (Students students2 : students) {
				System.out.println(students2);
			}
		}

	}

	@Override
	public Students searchStudent() {
		System.out.println("Enter RollNo");
		int rollNo = sc.nextInt();
		for (Students students2 : students) {
			if (students2.getRollNo() == rollNo) {
				System.out.println(students2);
				return students2;
			}
			System.out.println("student not found");
		}
		return null;

	}

	@Override
	public void updateStudent() {
		Students ss = searchStudent();
		if (ss != null) {
			System.out.println("ENter Name");
			String name = sc.nextLine();
			sc.nextLine();
			System.out.println("Enter Marks");
			double marks = sc.nextDouble();
			ss.setName(name);
			ss.setMarks(marks);

			System.out.println("student update sucessfull");
		}

	}

	@Override
	public void deleteStudent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayCourses() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		StudentsCRUD stud = new StudentsCRUD();
		stud.addStudent();
//		stud.viewStudents();
//		stud.searchStudent();
		stud.updateStudent();
	}

}