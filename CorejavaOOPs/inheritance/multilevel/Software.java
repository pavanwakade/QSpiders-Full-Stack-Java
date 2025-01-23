package inheritance.multilevel;

public class Software {
	
String name;
String  type;
String ceo;
double ternover;

public Software()
{}

public Software(String name, String type, String ceo, double ternover) {
	super();
	this.name = name;
	this.type = type;
	this.ceo = ceo;
	this.ternover = ternover;
}

public void DisplaySoftware(){
	System.out.println("Company Name     :"+name);
	System.out.println("Company Type     :"+type);
	System.out.println("Company CEO      :"+ceo);
	System.out.println("Company TernOver :"+ternover);
	System.out.println("---------------------------------");
}
}
