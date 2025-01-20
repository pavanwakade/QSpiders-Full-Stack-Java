package pattern;
class Patternn
{
    public static void main(String[] args)
	{
        int n = 10;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println("Hello World!!!");
				if(i==n/2){
					System.out.println("*"+" ");
				}
				else{
					System.out.println(" "+" ");
				}
            }
        }
    }
}