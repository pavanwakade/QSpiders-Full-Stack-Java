package assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class product {

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

	public static void insertData() {
		PreparedStatement stm = null;
		try {
			// Correct SQL query
			stm = conn.prepareStatement("INSERT INTO product VALUES (?, ?, ?)");

			// Collect user input
			System.out.println("Enter id: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter Name: ");
			String name = sc.nextLine(); // Read full name

			System.out.println("Enter price: ");
			int price = sc.nextInt();

			// Set parameters
			stm.setInt(1, id);
			stm.setString(2, name);
			stm.setInt(3, price);

			// Execute the query
			stm.executeUpdate();
			System.out.println("product inserted successfully.");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void fetchProduct() {

		try {
			// Use PreparedStatement for parameterized queries
			stm = conn.prepareStatement("SELECT * FROM product WHERE id = ?");
			System.out.println("Enter id to fetch details:");
			stm.setInt(1, sc.nextInt());

			rs = stm.executeQuery();

			if (rs.next()) {
				System.out.println("id: " + rs.getInt("id"));
				System.out.println("Product Name: " + rs.getString("name"));
				System.out.println("Price: " + rs.getString("price"));
			} else {
				System.out.println("No record found for the provided id.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateProduct() {

		try {
			// Use PreparedStatement for parameterized queries
			stm = conn.prepareStatement("UPDATE product SET name=? WHERE id=?");
			System.out.println("Enter ID to update:");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter name to update:");
			String name = sc.nextLine();

			// Set parameters for the PreparedStatement
			stm.setString(1, name);
			stm.setInt(2, id);

			// Execute the update statement
			stm.executeUpdate();

			System.out.println("Product updated successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteProduct() {
		try {
			// Use PreparedStatement for parameterized queries
			stm = conn.prepareStatement("delete from product WHERE id=?");
			System.out.println("Enter ID to delete:");
			int id = sc.nextInt();
			sc.nextLine();

			stm.setInt(1, id);

			// Execute the update statement
			stm.execute();

			System.out.println("Product deleted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void choice() {
//	while (true) {		
//		System.out.println();	

		System.out.println("Enter 1 for Insert Data Of Product");
		System.out.println("Enter 2 for Display Data Of Product");
		System.out.println("Enter 3 for Update Data Of Product");
		System.out.println("Enter 4 for Delete Data Of Product");
		System.out.println("Enter 5 for Exit");
		int choice = sc.nextInt();
		switch (choice) {

			case 1: {
				insertData();
				break;
			}

			case 2: {
				fetchProduct();
				break;
			}

			case 3: {
				updateProduct();

				break;
			}

			case 4: {
				deleteProduct();
				break;
			}

			case 5: {
				System.out.println("Exited.....");
				break;
			}

			default:
				System.out.println("Invalid Input Try Again....");
				break;
		}
	}

	public static void main(String[] args) throws SQLException {

		try {
			choice();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}