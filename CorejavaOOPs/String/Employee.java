/**
 * 
 */
package String;

/**
 * 
 */
public class Employee {

	private int id;

	private String Fname;

	private String Lname;

	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param fname
	 * @param lname
	 */
	public Employee(int id, String fname, String lname) {
		super();
		this.id = id;
		Fname = fname;
		Lname = lname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return Fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		Fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return Lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		Lname = lname;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", Fname=" + Fname + ", Lname=" + Lname + "]";
	}

	
	
}
