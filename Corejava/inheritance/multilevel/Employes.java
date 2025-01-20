package inheritance.multilevel;

public class Employes extends TCS {
    String ename;
    double Anualsal;
    String post;
    int Eexperiance;
    int age;

    public Employes() {
        // Default constructor
    }

    public Employes(String name, String type, String ceo, double ternover, String headQuartor, int employees, String loc, int projects,
                    String ename, double Anualsal, String post, int Eexperiance, int age) {
    	super(name, type, ceo, ternover, headQuartor, employees, loc, projects);

        // Initializing superclass variables
//        this.name = name;
//        this.type = type;
//        this.ceo = ceo;
//        this.ternover = ternover;
//        this.headQuartor = headQuartor;
//        Employees = employees;
//        this.loc = loc;
//        this.projects = projects;

        // Initializing current class variables
        this.ename = ename;
        this.Anualsal = Anualsal;
        this.post = post;
        this.Eexperiance = Eexperiance;
        this.age = age;
    }

    

	public void DisplayEmp() {
        System.out.println("Employee Name        : " + ename);
        System.out.println("Employee AnnualSalary: " + Anualsal);
        System.out.println("Employee Post        : " + post);
        System.out.println("Employee Experiance  : " + Eexperiance);
        System.out.println("Employee Age         : " + age);
    }

    @Override
    public String toString() {
        return "Employes [ename=" + ename + ", Anualsal=" + Anualsal + ", post=" + post +
               ", Eexperiance=" + Eexperiance + ", age=" + age + "]";
    }
}
