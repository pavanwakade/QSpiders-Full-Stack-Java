package postgressql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowTablesResultSetPostgresql
{
	  //postgresql variables
	static String url="jdbc:postgresql://localhost:5432/qsp";
	static String user="postgres";
	static String pass = "root";
	
	public static void main(String[] args)
	{
		
//		connection();
//	}
//	
//	public static void connection() {
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
			ResultSet res=stm.executeQuery("select * from employee");
//			ResultSet res1=stm.executeQuery("select * from student");
			
			System.out.println(res);
//			System.out.println(res1);
			
			while(res.next())
			{
				System.out.println(res.getInt("id"));
				System.out.println(res.getString("name"));
			}
			//5th step
			con.close();
		}
		catch(ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
		}
//		finally {
//			con.close();
//		}
	}
}
