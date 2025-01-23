package Typecasting;
class NarrowingShortToAll
{
public static void main(String [] args)
	
	{
		
		//all to short
	double a =1000.00000;
	short b=(short)a;
      System.out.println(b);
	 
	  long d =1000000000l;
	  short e=(short)d;
   System.out.println(e);
   	 
	  float c =1000.00f;
	  short f=(short)c;
      System.out.println(f);
	  
	  int g=10000;
	  short h=(short)g;
	  System.out.println(h);
	  
	  char k='A';
	  short l=(short)k;
	  System.out.println(l);
	
	}
}