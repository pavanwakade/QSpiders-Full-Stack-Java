package postgressql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertDataInpostgresFromUser {
	
	public static void main(String[] args) {
	 String url = "jdbc:postgresql://localhost:5432/qsp";
     String user = "postgres";
     String pass = "root";

     try {
         Class.forName("org.postgresql.Driver");
         System.out.println("PostgreSQL Driver class is loaded");
         
         Connection con = DriverManager.getConnection(url, user, pass);
         System.out.println("PostgreSQL Connection is created");
         
         // Prepare the update query
         PreparedStatement conn = con.prepareStatement("insert into student values(?,?,?)");
         
         // Set the parameters for the query
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the Student RollNO");
         int rollno=sc.nextInt();
         
         System.out.println("Enter the Student Name");
         String Uname=sc.next();
         
         System.out.println("Enter the Student Class");
         String classs=sc.next();
         
         conn.setInt(1, rollno);
         conn.setString(2, Uname);
         conn.setString(3, classs);
         
         
         // Execute the update query
         conn.execute();
         System.out.println("record Inserted.....");
         
         // Close the connection
         con.close();
     } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
     }
 }
}
