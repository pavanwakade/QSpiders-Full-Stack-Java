package loop;
class leapYearCount
{
	public static void main(String[] args) 
	{
		int year=1,Eyear=2000,count=0;
		for(;year<=Eyear;year++)
		{
          if((year%4==0 && year%100!=0) || (year%400==0))
		  {
			 System.out.println(year);
			 count++;
		   }
		}
		System.out.println("count of leap year is "+count);
	}
}
