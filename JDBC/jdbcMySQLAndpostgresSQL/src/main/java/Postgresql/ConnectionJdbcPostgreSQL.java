package Postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJdbcPostgreSQL {

public static void main(String[] args) {
		
		//postgresql variables
		String url="jdbc:postgresql://localhost:5432/qsp";
		String user="postgres";
		String pass = "root";
//		String url = "jdbc:postgresql://localhost:5432/qsp?user=postgres&password=root";

		try
		{
			//step 1
			
			//postgresql connection
			Class.forName("org.postgresql.Driver");
			System.out.println("postgresql Driver class is loaded");
			//2
			Connection  con=DriverManager.getConnection(url,user,pass);
//			Connection  con=DriverManager.getConnection(url);
			System.out.println("postgresql Connection is created");
			
			con.close();
		}
		catch(ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
		}
	}
}