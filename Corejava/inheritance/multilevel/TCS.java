package inheritance.multilevel;

public class TCS extends Software{
	String headQuartor;
	int Employees;
	String loc;
	int projects;
	
	public TCS()
	{
		
	}

	public TCS(String name, String type, String ceo, double ternover,String headQuartor, int employees, String loc, int projects) {
		super(name, type, ceo, ternover);
		this.headQuartor = headQuartor;
		Employees = employees;
		this.loc = loc;
		this.projects = projects;
	}
	
	

	public void DisplayTCS()
	{
		System.out.println("HeadQuartor     :"+headQuartor);
		System.out.println("Total Employees :"+Employees);
		System.out.println("Location        :"+loc);
		System.out.println("Total Projects  :"+projects);
		System.out.println("---------------------------------");
	}

}
