package MySQL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;
public class ConnectionJdbcMySQL {
	

	public static void main(String[] args) {
		
		//MySQL variables
		String url="jdbc:mysql://localhost:3306/qsp";
		String user="root";
		String pass = "pavan";
		
		try
		{
			//step 1
			
			//MySQL connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL Driver class is loaded");
			//2
			Connection  con=DriverManager.getConnection(url,user,pass);
			System.out.println("MySQL Connection is created");
			
			con.close();
		}
		catch(ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
		}
	}
}
