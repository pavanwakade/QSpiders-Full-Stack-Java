package abstraction;

public class calculatorDriver {

	public static void main(String[] args) {
		calculator c1=new Calupdate1() ;
		c1.addition(1, 1);
		c1.sub(8, 4);
		c1.mul(8, 8);
		c1.div(8, 2);
		//c1.message();  // (CTE  cant acess message method  bcoz message() not present in parent class and upcasting)
		
		
		
	}

}
