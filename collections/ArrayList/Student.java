package ArrayList;

public class Student implements Comparable<Student> {
	String name;
	int yop;
	double per;
	
	public Student() {
		super();
	}
	
	public Student(String name, double per,int yop) {
		super();
		this.name = name;
		this.per = per;
		this.yop = yop;
	}
	
	@Override
	public int compareTo(Student o) {
        return Double.compare(this.per, o.per);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", percentage=" + per + ", yop=" + yop + "]";
	}
}
