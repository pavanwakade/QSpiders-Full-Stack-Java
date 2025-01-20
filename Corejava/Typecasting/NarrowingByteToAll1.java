package Typecasting;
class NarrowingByteToAll1
{
public static void main(String [] args)
	
	{
		
		//all to byte
	double a =10000.000;
	byte b=(byte)a;
      System.out.println(b);
	 
	long d =100000;
	  byte e=(byte)d;
   System.out.println(e);
   	 
	  float c =10000.6f;
	  byte f=(byte)c;
      System.out.println(f);
	  
	  int g=10000;
	  byte h=(byte)g;
	  System.out.println(h);
	  
	  short i=1000;
	  byte j=(byte)i;
	  System.out.println(j);
	  
	  char k='a';
	  byte l=(byte)k;
	  System.out.println(l);
	
	}
}