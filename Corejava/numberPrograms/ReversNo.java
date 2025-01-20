package numberPrograms;
class ReversNo
	{
	public static void main(String[] args) 
		{
		 int num1=123;
		 int num=num1;
		 int last=1;
		 int rev=0;
		 while (num>0)
		  {
			  last=num%10;
			  rev =rev*10+last;
			  num=num/10;
		  }
		  System.out.println(rev);
	    }
   }
   