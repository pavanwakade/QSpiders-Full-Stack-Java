package AWT_Log_in;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private String name;
	private String password;
	private String phno;
	private String email;
	private String dob;
	private String gender;

	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param password
	 * @param phno
	 * @param email
	 * @param dob
	 * @param gender
	 */
	public User(String name, String password, String phno, String email, String dob, String gender) {
		super();
		this.name = name;
		this.password = password;
		this.phno = phno;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the phno
	 */
	public String getPhno() {
		return phno;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String oldPass, String password) {
		if (this.password.equals(oldPass)) {
			this.password = password;
		}
	}

	/**
	 * @param phno the phno to set
	 */
	public void setPhno(String phno) {
		this.phno = phno;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", phno=" + phno + ", email=" + email + ", dob=" + dob
				+ ", gender=" + gender + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dob, email, gender, name, password, phno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(phno, other.phno);
	}

	
//	@Override
//	public String toString() {
//		return "User [name=" + name + ", password=" + password + ", phno=" + phno + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(name, password, phno);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (!(obj instanceof User)) {
//			return false;
//		}
//		User other = (User) obj;
//		return Objects.equals(password, other.password) && Objects.equals(phno, other.phno);
//	}

//	@Override
//	public String toString() {
//		return "[name=" + name + ", password=" + password + ", phno=" + phno + "]";
//	}
//
//	public boolean equals(Object o) {
//		User ip = (User) o;
//		return this.password == ip.password && this.phno == ip.phno;
//	}
//
//	public int hashCode() {
//		return password.hashCode() + phno.hashCode();
//
//	}

}