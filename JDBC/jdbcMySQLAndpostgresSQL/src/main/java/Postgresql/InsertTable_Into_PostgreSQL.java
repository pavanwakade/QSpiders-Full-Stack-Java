package Postgresql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTable_Into_PostgreSQL {
	public static void main(String[] args) {
	
	//postgresql variables
	String url="jdbc:postgresql://localhost:5432/qsp";
	String user="postgres";
	String pass = "root";
//	String Url="jdbc:postgresql://localhost:5432/qsp?user=postgres&password=root";
	try
	{
		//step 1
		//postgresql connection
		Class.forName("org.postgresql.Driver");
		System.out.println("postgresql Driver class is loaded");
		//Step2
		Connection con=DriverManager.getConnection(url,user,pass);
		System.out.println("postgresql Connection is created");

		//step3
		Statement stm=con.createStatement();
		
		//step4
		boolean res=stm.execute("insert into employee values(1,'pavan','developer',500000,'10-2-2024'),(2,'Ravan','Hacker',5000000,'10-2-2024'),(3,'shshi','Tester',100000,'10-2-2024'),(4,'Sonali patil','Developer',1000000,'10-1-2024')");
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