package postgressql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateStudent {
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
            PreparedStatement conn = con.prepareStatement("UPDATE student SET name = ? WHERE rollno = ?");
            
            // Set the parameters for the query
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Student RollNO");
            int ids=sc.nextInt();
            
            System.out.println("Enter the Student RollNO");
            String Uname=sc.next();
            conn.setString(1, Uname);
            conn.setInt(2, ids);
            
            // Execute the update query
            int rowsUpdated = conn.executeUpdate();
            System.out.println(rowsUpdated + " record(s) updated.");
            
            // Close the connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
