/**
 * 
 */
package StudentManagement;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public class StudentsCRUD {
	static List<Students> students = new LinkedList<>();

	public static void main(String[] args) {

		students.add(new Students(1, "pavan", 100.0));
		students.add(new Students(2, "Ravan", 35.0));
		students.add(new Students(3, "Jivan", 10.0));
		students.add(new Students(4, "Savan", 50.0));
		students.add(new Students(5, "Ram", 100.0));
		getAllStudents();
		System.out.println(getStudentByRollNo(33));
		
		System.out.println(updateStudent(4, "pavan", 100.0));
		getAllStudents();
	}

	public static void getAllStudents() {
		for (Students students2 : students) {
			System.out.println(students2);
		}
	}

	public static Students getStudentByRollNo(int rollNo) {

		for (Students students2 : students) {

			if (students2.getRollNo() == rollNo) {
				return students2;
			}
		}
		return null;
	}

	static boolean updateStudent(int rollNo, String newName, double newMarks) {

		for (Students students2 : students) {
			if (students2.getRollNo() == rollNo) {

				students2.setName(newName);
				students2.setMarks(newMarks);
				return true;
			}
		}
		return false;
	}
	
	
	 boolean deleteStudent(int rollNo) {
		 

		 
		return false;
		 
	 }
}