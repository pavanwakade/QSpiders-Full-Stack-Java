package Methods;
import java.util.Scanner;

public class Login
{
	static String username="pavan";
	static String password=" pavan123";
	static String dob="10-Aug-1999";
	static String city="latur";
	
	public static void main(String[] args) 
	{
		displayUser();
		
		
	}
	public static String username(String username) 
	{
		return username;
			
		}
	public static String password(String Password) 
	{
		
	   return 	Password;

		}
	public static String age(String age) 
	{
		
	  return age;

		}
     public static String dob(String dob) 
     {

     return dob;
       }
     public static String city(String city) 
     {

     return city;
       }
     
  
     //Display   
    public static void displayUser()
    {
    	System.out.println("Hello "+username(username));
    	System.out.println("password : "+password(username));
    	System.out.println("DOB : "+dob(dob));
    	System.out.println("city : "+city(city));
    	 
     }
}
