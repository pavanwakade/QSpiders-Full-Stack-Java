package AWT_Log_in;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DataBase {

	static ArrayList<User> db = new ArrayList<User>();
	static {
		FileInputStream fin;
		try {
			fin = new FileInputStream("D:\\coding with pavan\\Qspiders\\java\\internship\\src\\gui\\userdb.txt");
			ObjectInputStream Oin = new ObjectInputStream(fin);

			db = (ArrayList<User>) Oin.readObject();

			System.out.println("Data loaded");
		} catch (FileNotFoundException e) {

			System.out.println("File not found");
		} catch (IOException oi) {
			System.out.println("No objects in the file");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the db
	 */
	public static ArrayList<User> getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public static void setDb(ArrayList<User> db) {
		DataBase.db = db;
	}

	public DataBase() {
		// TODO Auto-generated constructor stub
	}

	public static boolean addUser(String name, String password, String phno, String email, String dob, String gender) {
		User obj = new User(name,phno, password, email,dob,gender);

		if (!containsUser(db, obj)) {
			db.add(obj);
			System.out.println(db);
			return true;
		}
		return false;

	}

	public static boolean containsUser(ArrayList<User> al, User obj) {
	    for (User user : al) {
	        if (user.getPhno().equals(obj.getPhno()) && user.getPassword().equals(obj.getPassword())) {
	            return true; 
	        }
	    }
	    return false; 
	}


}