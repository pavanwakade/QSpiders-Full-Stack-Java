package inheritance.multilevel;

public class SoftdriverDriver {

	public static void main(String[] args) {
	
		Employes e1=new Employes("TCS","Servise Based","Tejus",9999999.0,"Pune",210000,"pune",12000,"Pavan Wakade",600000,"developer",1,25);
		e1.DisplaySoftware();
		e1.DisplayTCS();
		e1.DisplayEmp();
		
		System.out.println(e1.toString());  //toString Method 
		System.out.println(e1);  //toString Method 
	}
}
