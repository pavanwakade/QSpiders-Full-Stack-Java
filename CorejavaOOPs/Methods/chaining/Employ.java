package Methods.chaining;

public class Employ {
    String name;
    String adhar;
    
	public Employ() {
		super();
	}

	public Employ(String name, String adhar) {
        this.name = name;
        this.adhar = adhar;
    }

    public Employ name() {
        System.out.println("Name : " + name);
        return this;
    }

    public Employ adhar() {
        System.out.println("Adhar: " + adhar);
        return this;
    }
}
