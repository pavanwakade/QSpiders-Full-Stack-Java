package toString;

public class Doctor {
	String name;
	int age;
	public Doctor() {
	}
	public Doctor(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "[Name ="+name+", Age= "+age+"]";
		
	}
	
	
	public boolean equals(Object o) {
		Doctor d1= (Doctor)o;
		if(this.name==d1.name&&this.age==d1.age)
		{
			return true;
		}
		return false;
	}
	

	public int hashCode() {
		int dz=name.hashCode()+age;
		return dz;
		
		
	}
	
	}

