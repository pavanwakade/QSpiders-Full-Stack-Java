package Typecasting;
class NarrowingCharToAll
{
public static void main(String [] args)
	
	{
		
		//all to char
	double a =1000.0000;
	char b=(char)
		a;
      System.out.println(b);
	 
	  long d =100000;
	  char e=(char)d;
   System.out.println(e);
   	 
	  float c =1000.00f;
	  char f=(char)c;
      System.out.println(f);
	  
	  int g=1000;
	  char h=(char)g;
	  System.out.println(h);
	  
	short i=1000;
	char j=(char)i;
	System.out.println(j);
	
	}
}