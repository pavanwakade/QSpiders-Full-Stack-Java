package loop;
class AlternateAlphabate
{
	public static void main(String[] args) 
	{
		int start=65;
		int end=90;
		// 
		for(;start<end;start++)
		{
		 System.out.println((char)start);
		 start+=1;
		}
	}
}
