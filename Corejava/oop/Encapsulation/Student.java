package oop.Encapsulation;

import java.util.Objects;

public class Student {
	 
	private String name;
	 private int id;
	 long moNo;
	 
	 public Student() {
			super();
		}

	public Student(String name, int id, long moNo) {
		super();
		this.name = name;
		this.id = id;
		this.moNo = moNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public long getMoNo() {
		return moNo;
	}

	public void setMoNo(long moNo) {
		this.moNo = moNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, moNo, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return id == other.id && moNo == other.moNo && Objects.equals(name, other.name);
	}
	
}
