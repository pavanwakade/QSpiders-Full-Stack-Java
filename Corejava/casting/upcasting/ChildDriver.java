package casting.upcasting;

public class ChildDriver {

	public static void main(String[] args) {
	
		chaild c1=new chaild(); //normal way he can access both members
		//c1.smoker();
		//c1.drinking(); 
		
		Fther f1=c1;   //UPCASTING    HE CAN access only father member not child members
		f1.drinking();
		//f1.smoker();//CTE
	}
}
