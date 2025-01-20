package toString;

public class DctorDriver {

	public static void main(String[] args) {
		
		Doctor d1=new Doctor("Shashi",24);
		System.out.println(d1);
		
		Doctor d2=new Doctor("Shashi",24);
		System.out.println(d2);    //basause of override toString it Display [Name =Shashi, Age= 24] otherwise it Display Address of object
		
		Doctor d3=new Doctor("pavan",25);
		System.out.println(d3);
		
//		System.out.println(d1.toString());
//		System.out.println(d2.toString());
		
		System.out.println(d1==d2);
		System.out.println(d2==d3);
		
		System.out.println("d1.equals(d2)= "+d1.equals(d2));
		System.out.println("d1.equals(d3)= "+d1.equals(d3));
		
		System.out.println(d1.hashCode());
		System.out.println(d2.hashCode());
		System.out.println(d3.hashCode());

		System.out.println(d1.getClass());
		System.out.println(d1.getClass().getName()+"@"+Integer.toHexString(d1.hashCode()));
	}

}
