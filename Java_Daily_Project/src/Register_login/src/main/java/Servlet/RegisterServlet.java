package Servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import postgresqlConn.PostConnection;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

	static {
		PostConnection.CreateConnection();
	}

	static String UserName;
	static String DOB;
	static String Gender;
	static String MoNo;
	static String Email;
	static String Pass;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String mob = req.getParameter("mob");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		if (namevalidation(name)) {
			if (dobalidation(dob)) {
				java.util.Date parsedDate = null;
				try {
					// Parse the dob string to java.util.Date first
					parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// Convert java.util.Date to java.sql.Date
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

				if (gendervalidation(gender)) {
					if (mobValidation(mob)) {
						if (emailValidation(email)) {
							if (validationPassword(pass)) {
								UserName = name;
								DOB = dob;
								Gender = gender;
								MoNo = mob;
								Email = email;
								Pass = pass;
								System.out.println(PostConnection.CreateConnection());
								if (PostConnection.CreateConnection()) {

									try {
										String query = "insert into users values(?,?,?,?,?,?)";
										PreparedStatement stm = PostConnection.conn.prepareStatement(query);
										stm.setString(1, MoNo);
										stm.setString(2, Email);
										stm.setString(3, UserName);
										stm.setDate(4, sqlDate);
										stm.setString(5, Gender);
										stm.setString(6, Pass);

										stm.execute();
										System.out.println("Executed");

										System.out.println(UserName);
										System.out.println(Gender);
										System.out.println(DOB);
										System.out.println(MoNo);
										System.out.println(Email);
										System.out.println(Pass);

										RequestDispatcher rp = req.getRequestDispatcher("login.html");
										rp.forward(req, resp);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} finally {
										try {
											if (PostConnection.conn != null) {
												PostConnection.conn.close();
											}
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static boolean namevalidation(String name) {
		if (name != null) {

			if (name.length() > 3) {
				return true;
			}
		}
		return false;
	}

	public static boolean dobalidation(String dob) {
		if (dob != null && !dob.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false); // Disables lenient parsing (ensures valid date)
			try {
				// Try to parse the date string
				java.util.Date parsedDate = sdf.parse(dob);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

				// Check if the date is not in the future
				if (sqlDate.before(new java.sql.Date(System.currentTimeMillis()))) {
					return true;
				}
				System.out.println("DOB cannot be in the future.");
			} catch (ParseException e) {
				System.out.println("Invalid date format");
			}
		} else {
			System.out.println("DOB cannot be empty");
		}
		return false;
	}

	public static boolean gendervalidation(String gender) {
		if (gender != null) {
			gender = gender.trim().toLowerCase();
			if (gender.equals("male") || gender.equals("female") || gender.equals("other")) {
				return true;
			}
		} else {
			System.out.println("Gender can not Empty");
		}
		return false;
	}

	public static boolean mobValidation(String mob) {
		// Check if the mobile number is not null
		if (mob != null) {
			// Check if the length of the mobile number is 10
			if (mob.length() == 10) {
				// Check if the first character is one of the valid starting digits
				char firstChar = mob.charAt(0);
				if (firstChar == '9' || firstChar == '8' || firstChar == '7' || firstChar == '6') {
					// Check if all characters are digits
					for (int i = 0; i < mob.length(); i++) {
						if (!Character.isDigit(mob.charAt(i))) {
							return false; // Return false if any character is not a digit
						}
					}
					return true; // Return true if all conditions are met
				}
			}
		} else {
			System.out.println("Mobile number cannot be empty.");
		}
		return false; // Return false for invalid mobile number
	}

	public static boolean emailValidation(String email) {
	    // If email is null or empty, return true (indicating it's valid)
	    if (email == null || email.isEmpty()) {
	        return true;
	    }

	    // If email is not null, perform format validation
	    if (email.contains("@") && email.contains(".")) {
	        int atIndex = email.indexOf('@');
	        int dotIndex = email.lastIndexOf('.');

	        // Ensure '@' comes before '.' and both are not at the beginning or end
	        if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1) {
	            return true;
	        }
	    }

	    System.out.println("Email Not Correct");
	    return false;
	}


	public boolean validationPassword(String pass) {

		boolean upper, lower, special, digit;
		upper = lower = special = digit = false;

		if (pass.length() >= 8) {
			for (int i = 0; i < pass.length(); i++) {
				char ch = pass.charAt(i);

				if (ch >= 'a' && ch <= 'z') {

					lower = true;

				}

				else if (ch >= 'A' && ch <= 'Z') {
					upper = true;

				}

				else if (ch >= '0' && ch <= '9') {

					digit = true;
				}

				else {
					special = true;
				}
				if (lower && upper && special && digit) {
					break;
				}

			}
			return lower && upper && special && digit;
		} else {
			System.out.println("password must be 8 charactor or above");
		}
		return false;
	}
}
