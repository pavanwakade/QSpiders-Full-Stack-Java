package Switch;
import java.util.Scanner;
public class AllLeapYear
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        check();
    }

    public static void check()
    {
        System.out.println("Select Option");
        System.out.println("1. Check leap or not");
        System.out.println("2. Check leap years up to nth year");
        System.out.println("3. Count leap years between a range");
        System.out.println("4. Find the nth leap year from start year");
        System.out.println("5. Find the Next leap year");
        int ip = sc.nextInt();

        switch (ip) 
        {
            case 1:
            {
                System.out.println("Enter Year");
                leapYear(sc.nextInt());
                break;
            }
            case 2:
            {
                System.out.println("Enter Number of Years to Check");
                nthLeapYear(sc.nextInt());
                break;
            }
            case 3: 
            {
                System.out.println("Enter Start Year");
                int startYear = sc.nextInt();
                System.out.println("Enter End Year");
                int endYear = sc.nextInt();
                countLeapYear(startYear, endYear);
                break;
            }
            case 4: 
            {
                System.out.println("Enter Starting Year");
                int startYear = sc.nextInt();
                System.out.println("Enter Nth Number");
                int n = sc.nextInt();
                nextNthLeapYear(startYear, n);
                break;
            }
            
            case 5:
            {
            	System.out.println("Enter Year");
                int Nyear = sc.nextInt();
                nextLyear(Nyear);
                break;
            }
            default: 
            {
                System.out.println("You Entered a Wrong Option");
            }
        }
    }

    public static void nthLeapYear(int y) 
    {
        int count = 0;
        for (int year = 1; count < y; year++)
        {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 
            {
                System.out.println(year + " is a Leap Year!");
                count++;
            }
        }
    }

    public static void leapYear(int y)
    {
        if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) 
        {
            System.out.println(y + " is a Leap Year!");
        } 
        else 
        {
            System.out.println(y + " is Not a Leap Year!");
        }
    }

    public static void countLeapYear(int startYear, int endYear) 
    {
        int count = 0;
        for (int year = startYear; year <= endYear; year++) 
        {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 
            {
                count++;
            }
        }
        System.out.println("Total Leap Years: " + count);
    }

    public static void nextNthLeapYear(int startYear, int n) 
    {
        int count = 0;
        int year = startYear;
        while (count < n) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 
            {
                count++;
            }
            year++;
        }
        System.out.println( n + "th leap year from " + startYear + " is " + (year - 1));
    }
    
    
    public static void nextLyear(int nxyear)
    {
    	if ((nxyear % 4 == 0 && nxyear % 100 != 0) || (nxyear % 400 == 0))
    	{
    		System.out.println(nxyear+" is a Leap Year");
    	}
    	else
    	{
    		int nyear=nxyear;
    		while(true)
    		{
    			if ((nyear % 4 == 0 && nyear % 100 != 0) || (nyear % 400 == 0))
    			{
    				System.out.println(nyear+" is a Next Leap Year After "+nxyear);
    				break;
    			}
    			nyear++;
    		}
    	}
    }
}
