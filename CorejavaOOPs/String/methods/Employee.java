/**
 * 
 */
package String.methods;

/**
 * 
 */
public class Employee{
	String id;
	String name;
	String email;
	String age;
	String country;
	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param age
	 * @param country
	 */
	public Employee(String id, String name, String email, String age, String country) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.country = country;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", country=" + country
				+ "]";
	}
}