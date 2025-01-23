package loopWhile;

class LeapYearEven
{
	public static void main(String[] args)
	{
		int sYear=1;
		int eYear=2000;
		int count=0;
		while (sYear<eYear)
		{
			if ((sYear%4==0 && sYear%100!=0) || (sYear%400==0) )
			{ 
				if (sYear%2==0)
				{	
					System.out.println(sYear+"  is a even leap year");
					count++;
				}
			}
			sYear++;
		}
	     	System.out.println(count);
	}
}

