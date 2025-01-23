package loopWhile;

class   leapWhile
{
	public static void main(String[] args)
	{
		int year=1;
		int check=2000;
		int count=0;
		while (year<check)
		{
			if ((year%4==0 && year%100!=0) || (year%400==0) )
			{ 
				System.out.println(year+"  is a leap year");
				count++;
			}
			year++;
		}
		System.out.println("total leap year : "+count);
	}
}
