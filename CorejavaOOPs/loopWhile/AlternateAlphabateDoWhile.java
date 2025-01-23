package loopWhile;

class AlternateAlphabateDoWhile
{
	public static void main(String[] args)
	{
		int start=1;
		int end=10;
		do
		{
			if (start%2==0)
			{
				System.out.println(start);
			}
			start++;
		}
		while (start<=end);
	}
}