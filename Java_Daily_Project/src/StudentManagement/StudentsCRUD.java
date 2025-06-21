/**
 * 
 */
package StudentManagement;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 */
public class StudentsCRUD implements StudentsInterface {
	static List<Students> students = new LinkedList<>();

	public static void main(String[] args) {
		StudentsCRUD ssCrud = new StudentsCRUD();
		
		ssCrud.addStudent(new Students(0, null, null));

		ssCrud.getAllStudents();
		System.out.println(ssCrud.getStudentByRollNo(33));

		System.out.println(ssCrud.updateStudent(4, "pavan", 100.0));
		System.out.println(ssCrud.deleteStudent(4));
		ssCrud.getAllStudents();
	}

	public List getAllStudents() {
		for (Students students2 : students) {
			System.out.println(students2);
		}
		return null;
	}

	public Students getStudentByRollNo(int rollNo) {

		for (Students students2 : students) {

			if (students2.getRollNo() == rollNo) {
				return students2;
			}
		}
		return null;
	}

	public boolean updateStudent(int rollNo, String newName, double newMarks) {

		for (Students students2 : students) {
			if (students2.getRollNo() == rollNo) {
				students2.setName(newName);
				students2.setMarks(newMarks);
				return true;
			}
		}
		return false;
	}

	public boolean deleteStudent(int rollNo) {

		ListIterator<Students> stuIterator = students.listIterator();
		while (stuIterator.hasNext()) {
			Students stu = stuIterator.next();
			if (stu.getRollNo() == rollNo) {
				stuIterator.remove();
				System.out.println("Student removed with roll no " + rollNo);
				return true;
			}
		}
		return false;
	}

	public List<Students> sortByMarks() {
		return null;

	}

	@Override
	public void addStudent(Students student) {
		students.add(new Students(1, "pavan", 100.0));
		students.add(new Students(2, "Ravan", 35.0));
		students.add(new Students(3, "Jivan", 10.0));
		students.add(new Students(4, "Savan", 50.0));
		students.add(new Students(5, "Ram", 100.0));

	}

	@Override
	public List<Students> sortByName() {
		// TODO Auto-generated method stub
		return null;
	}
}