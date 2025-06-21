/**
 * 
 */
package StudentManagement;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class StudentsCRUD implements StudentsInterface {
	List<Students> students = new LinkedList<>();
	static Scanner sc = new Scanner(System.in);

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
			sc.nextLine();
			System.out.println("ENter Name");
			String name = sc.nextLine();
			System.out.println("Enter Marks");
			double marks = sc.nextDouble();
			ss.setName(name);
			ss.setMarks(marks);
			System.out.println("student update sucessfull");
		}
	}

	@Override
	public void deleteStudent() {
		Students ssStudents= searchStudent();
		if (ssStudents!=null) {
			students.remove(ssStudents);
			System.out.println("Student deleted");
		}
	}

	@Override
	public void displayCourses() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		StudentsCRUD stud = new StudentsCRUD();
//		stud.addStudent();
//		stud.searchStudent();
//		stud.updateStudent();
//		stud.deleteStudent();
//		stud.viewStudents();
		
		  int choice;
	        do {
	            System.out.println("\n====== Student Record Management ======");
	            System.out.println("1. Add Student");
	            System.out.println("2. View All Students");
	            System.out.println("3. Search Student by Roll No");
	            System.out.println("4. Update Student");
	            System.out.println("5. Delete Student");
	            System.out.println("6. Display Unique Courses");
	            System.out.println("7. Exit");
	            System.out.print("Enter choice: ");
	            choice = sc.nextInt();

	            switch (choice) {
	                case 1:stud.addStudent(); break;
	                case 2:stud. viewStudents(); break;
	                case 3:stud. searchStudent(); break;
	                case 4:stud. updateStudent(); break;
	                case 5: stud.deleteStudent(); break;
	                case 6: stud.displayCourses(); break;
	                case 7: System.out.println("Exiting..."); break;
	                default: System.out.println("Invalid choice.");
	            }
	        } while (choice != 7);
	}

}