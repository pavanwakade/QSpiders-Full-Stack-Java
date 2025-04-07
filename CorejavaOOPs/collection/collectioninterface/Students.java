package collection.collectioninterface;

public class Students {
	
	public Students() {}
	
	String name;
	
	String add;
	
	long mob;

	/**
	 * @param name
	 * @param add
	 * @param mob
	 */
	public Students(String name, String add, long mob) {
		super();
		this.name = name;
		this.add = add;
		this.mob = mob;
	}

	@Override
	public String toString() {
		return "Students [name=" + name + ", add=" + add + ", mob=" + mob + "]";
	}
	
	
	
	
	

}
