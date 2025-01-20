package postgressql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertByUsingPrapardStatementPostgresql {
	
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
        
         
         conn.setInt(1, 105);
         conn.setString(2, "rahul");
         conn.setString(3, "11th");
         
         
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
