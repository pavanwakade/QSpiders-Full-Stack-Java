package postgresqlConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostConnection {

	static String URL = "jdbc:postgresql://localhost:5432/qsp?user=postgres&password=root";

	public static Connection conn;
//	public static void main(String[] args) {
//		boolean c = CreateConnection();
//		if (c) {
//			run();
//			System.out.println("Table created successfully.");
//		} else {
//			System.out.println("Table creation failed.");
//		}
//	}

	public static boolean CreateConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Database Connected");
			return true;
//			PreparedStatement stm = conn.prepareStatement(query);
//			stm.execute();
//			System.out.println("Executed");
//			conn.close();
//			return true;

		} catch (SQLException e) {
			System.out.println("Query not executed: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found: " + e.getMessage());
		}
		return false;
	}

	public static void run() {

		try {
//			String query = null;
			String query = "DELETE FROM users";

			PreparedStatement stm = conn.prepareStatement(query);
			stm.execute();
			System.out.println("Executed");
			conn.close();

		} catch (SQLException e) {
			System.out.println("Query not executed: " + e.getMessage());
		}
	}

}
