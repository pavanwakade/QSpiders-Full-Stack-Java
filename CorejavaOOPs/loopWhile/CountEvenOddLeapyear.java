package loopWhile;

class CountEvenOddLeapyear
{
	public static void main(String[] args)
	{
		int sYear=1;
		int eYear=2000;
		int evencount=0;
		int oddcount=0;
		while (sYear<eYear)
		{
			if ((sYear%4==0 && sYear%100!=0) || (sYear%400==0) )
			{ 
				if (sYear%2==0)
				{	
					evencount++;
				}
				else
				{
					oddcount++;
				}
			}
			sYear++;
		}
		System.out.println(evencount);
		System.out.println(oddcount);
	}
}

