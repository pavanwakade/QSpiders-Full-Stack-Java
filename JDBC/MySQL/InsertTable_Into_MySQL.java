package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTable_Into_MySQL {
	public static void main(String[] args) {
		
		//MySQL variables
		String url="jdbc:mysql://localhost:3306/qsp";
		String user="root";
		String pass = "pavan";
		
//		String Url="jdbc:mysql://localhost:3306//qsp?user=postgres&password=root";
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
			boolean res=stm.execute("insert into employee values(1,'pavan','developer',500000,'2024-10-1'),(2,'Ravan','Hacker',5000000,'2024-10-1'),(3,'shshi','Tester',100000,'2024-10-1'),(4,'Sonali patil','Developer',1000000,'2024-10-1')");
			boolean res1=stm.execute("insert into student values(1,'pavan','4th'),(2,'Ravan','4th'),(3,'shashi','3th'),(4,'sonali','5th')");
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