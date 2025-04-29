/**
 * 
 */
package Constructor;

/**
 * 
 */
public class CopyConstructorStudent {

	int id;

	String name;

	long mo;

	public CopyConstructorStudent(int id, String name, long mo) {
		this.id = id;
		this.name = name;
		this.mo = mo;
	}
	
	

	public CopyConstructorStudent(CopyConstructorStudent stu) {
		this.id = stu.id;
		this.name = stu.name;
		this.mo = stu.mo;
	}

	void displyStu() {
		System.out.println(" id :" + id + "\n name : " + name + "\n mobile : " + mo);

		System.out.println("--------------------");
	}

	public static void main(String[] args) {

		CopyConstructorStudent s1 = new CopyConstructorStudent(1, "pavan", 2345678);   

		s1.displyStu();

		CopyConstructorStudent s2 = new CopyConstructorStudent(s1.id, s1.name, s1.mo); // without copy construcor

		s2.displyStu();

		CopyConstructorStudent s3 = new CopyConstructorStudent(s1); // using copy construtor

		s3.displyStu();
	}
}
