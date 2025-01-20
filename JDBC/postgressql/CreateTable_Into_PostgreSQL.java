package postgressql;

import java.sql.*;

public class CreateTable_Into_PostgreSQL {
	public static void main(String[] args) {
			
			//postgresql variables
			String Url="jdbc:postgresql://localhost:5432/qsp";
			String user="postgres";
			String pass = "root";
//			String Url="jdbc:postgresql://localhost:5432/qsp?user=postgres&password=root";
			try
			{
				//step 1
				//postgresql connection
				Class.forName("org.postgresql.Driver");
				System.out.println("postgresql Driver class is loaded");
				//Step2
				Connection con=DriverManager.getConnection(Url,user,pass);
				System.out.println("postgresql Connection is created");

				//step3
				Statement stm=con.createStatement();
				
				//step4
				boolean res=stm.execute("CREATE TABLE employee(id INTEGER UNIQUE PRIMARY KEY NOT NULL,name VARCHAR(50),job VARCHAR(50),salary INT,hiredate DATE)");
				boolean res1=stm.execute("CREATE TABLE student(rollno INTEGER UNIQUE PRIMARY KEY NOT NULL,name VARCHAR(50),class VARCHAR(50))");
				
				System.out.println(res);
				System.out.println(res1);
				
				//5th step
				con.close();
			}
			catch(ClassNotFoundException |SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
