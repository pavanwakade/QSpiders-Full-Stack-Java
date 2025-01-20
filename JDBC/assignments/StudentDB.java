package assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDB {

	static String url = "jdbc:postgresql://localhost:5432/qsp";
	static String user = "postgres";
	static String password = "root";
	static Connection conn;
	static PreparedStatement stm;
	static ResultSet rs;

	static Scanner sc = new Scanner(System.in);

	static {
		try {
			// Correct driver class name
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver class loaded...");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected...");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void fetchTable() {

		try {
			Statement stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM student");

			while (rs.next()) {  // loop through the result set to get each row
	            int rollno = rs.getInt("rollno"); 
	            String name = rs.getString("name");

	            System.out.println("Roll No: " + rollno);
	            System.out.println("Name: " + name);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void insertStudentData() {
	    PreparedStatement stm = null;

	    try {
	        // Correct SQL query
	        stm = conn.prepareStatement("INSERT INTO student (rollno, name, class) VALUES (?, ?, ?)");

	        // Collect user input
	        System.out.println("Enter Roll No: ");
	        int rollNo = sc.nextInt(); // Read roll number
	        sc.nextLine(); // Consume the leftover newline character

	        System.out.println("Enter Name: ");
	        String name = sc.nextLine(); // Read full name

	        System.out.println("Enter Class: ");
	        String className = sc.nextLine(); // Read class name

	        // Set parameters
	        stm.setInt(1, rollNo);
	        stm.setString(2, name);
	        stm.setString(3, className);

	        // Execute the query
	        int rowsInserted = stm.executeUpdate(); // Use executeUpdate for INSERT queries

	        if (rowsInserted > 0) {
	            System.out.println("Student data inserted successfully.");
	        } else {
	            System.out.println("Failed to insert student data.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	        conn.close();
	    	}
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	

	public static void fetchDataFromDB() {

		try {
			// Use PreparedStatement for parameterized queries
			stm = conn.prepareStatement("SELECT * FROM student WHERE rollno = ?");
			System.out.println("Enter Roll No to fetch details:");
			stm.setInt(1, sc.nextInt());

			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("Roll No: " + rs.getInt("rollno"));
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("Class: " + rs.getString("class"));
			} else {
				System.out.println("No record found for the provided rollno.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void updateStudentData(){

		try {
			// Use PreparedStatement for parameterized queries
			stm = conn.prepareStatement("update student set name=? WHERE rollno = ?");
			System.out.println("Enter Roll No to update details:");
			int rollno = sc.nextInt(); 
		     
			System.out.println("Enter name to update:");
		    String name = sc.nextLine(); 
		    
			stm.setString(1, name);
		    stm.setInt(2, rollno);

			rs = stm.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void deleteStudentData(){

		try {
		    // Use PreparedStatement for parameterized queries
		    PreparedStatement stm = conn.prepareStatement("DELETE FROM student WHERE rollno = ?");
		    System.out.println("Enter Roll No to delete the student:");
		    int rollNo = sc.nextInt();
		    stm.setInt(1, rollNo);

		    int rowsAffected = stm.executeUpdate(); // Use executeUpdate for DELETE statements

		    if (rowsAffected > 0) {
		        System.out.println("Student with Roll No " + rollNo + " is deleted successfully.");
		    } else {
		        System.out.println("No student found with Roll No " + rollNo);
		    }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void choice() {
//		while (true) {		
//			System.out.println();	

		System.out.println("Enter 1 display full Table");
		System.out.println("Enter 2 for Insert Data Of Student");
		System.out.println("Enter 3 for Display Data Of Student");
		System.out.println("Enter 4 for Update Data Of Student");
		System.out.println("Enter 5 for Delete Data Of Student");
		System.out.println("Enter 6 for Exit");
		int choice = sc.nextInt();
		switch (choice) {
			
			case 1: {
				fetchTable();
				break;
			}
			
			case 2: {
				insertStudentData();
				break;
			}
			
			case 3: {
				fetchDataFromDB();
				break;
			}

			case 4: {
				updateStudentData();

				break;
			}

			case 5: {
				deleteStudentData();
				break;
			}
			
			case 6: {
				System.out.println("Exited.....");
				break;
			}
			
			default:
				System.out.println("Invalid Input Try Again....");
				break;
		}
	}
//	}

	public static void main(String[] args) {
		System.out.println("----------- Welcome To Student Database -----------");

		choice();
	}
}
