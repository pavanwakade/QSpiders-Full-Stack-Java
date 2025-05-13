package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable_Into_MySQL {

	public static void main(String[] args) {
				
				//MySQL variables
				String url="jdbc:mysql://localhost:3306/qsp";
				String user="root";
				String pass = "pavan";
				
//				String Url="jdbc:mysql://localhost:3306//qsp?user=postgres&password=root";
				try
				{
					//step 1
					
					//MySQL connection
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("MySQL Driver class is loaded");
					Connection  con=DriverManager.getConnection(url,user,pass);
					System.out.println("MySQL Connection is created");
					
					
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