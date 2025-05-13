package Postgresql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoreProcedure {

	public static void main(String[] args) {
		
		//postgresql variables
				String url="jdbc:postgresql://localhost:5432/qsp";
				String user="postgres";
				String pass = "root";
				
//				String Url="jdbc:postgresql://localhost:5432/qsp?user=postgres&password=root";
				try
				{
					//step 1
					
					//postgresql connection
					Class.forName("org.postgresql.Driver");
					System.out.println("postgresql Driver class is loaded");
					Connection  con=DriverManager.getConnection(url,user,pass);
					System.out.println("postgresql Connection is created");
					CallableStatement cs=con.prepareCall("call create_student(112,'virat','18th')");
					cs.execute();
					System.out.println("deta inserted....");
					con.close();
				}
				catch(ClassNotFoundException |SQLException e)
				{
					e.printStackTrace();
				}
			}
		}