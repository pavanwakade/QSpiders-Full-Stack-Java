/**
 * 
 */
package StudentManagement;

	import java.util.List;

	public interface StudentsInterface {

	    // Add a new student
	    void addStudent(Students student);

	    // View all students
	    List<Students> getAllStudents();

	    // Get student by roll number
	    Students getStudentByRollNo(int rollNo);

	    // Update student by roll number
	    boolean updateStudent(int rollNo, String newName, double newMarks);

	    // Delete student by roll number
	    boolean deleteStudent(int rollNo);

	    // Sort students by name (ascending)
	    List<Students> sortByName();

	    // Sort students by marks (descending)
	    List<Students> sortByMarks();
	}


