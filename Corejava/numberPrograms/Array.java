package numberPrograms;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {10,20,30,40,50};
		boolean numgreater=false;
		for(int num:arr) 
		{
		
			if(num>59) {
				System.out.println(0);
				numgreater=true;
			}	
		}
		
		if(!numgreater) {
			int sum=0;
			for(int num:arr)
			{
				if(num % 2==0)
				{
					sum+=num;
					
				}
			}
			System.out.println("addition of even no is = "+sum);
		}
	}
}
