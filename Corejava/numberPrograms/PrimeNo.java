package numberPrograms;
class PrimeNo
{
	public static void main(String[] args)
	{
	int number=7;
	int no=number;
		int deno=2;
		for(;deno<no;deno++)
		{
			if(no%deno==0)
			{
				break;
			}
		}
		if(no==deno)
		{
			System.out.println(number+" is prime no");
		}
		else
		{
			System.out.println(number+" is not a prime no");
		}
	}
}