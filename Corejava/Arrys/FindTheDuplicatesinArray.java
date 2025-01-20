package Arrys;
public class FindTheDuplicatesinArray {

    public static void main(String[] args) {
        int []a= {1,2,3,4,5,3,2,8,9,9,3,5,10,10};
        int []b = new int[a.length]; // Initialize the b array with a.length values
        int []c=new int[a.length];
        
        for (int i = 0; i < a.length-1; i++) {
            if (b[i]!=-1) {
                int count=0;
                if (b[i]!=-1) {
                for (int j = i+1; j < a.length; j++) {
                    if (a[i]==a[j]) {
                        count++;
                        b[j]=-1; // Mark the found duplicates in the b array
                    }
                   
                }
                b[i]=count; // Store the number of occurrences for each possible duplicate in the b array
            }
           }
         }

        for (int i = 0; i < a.length; i++) {
            if (b[i]!=-1) {
            	System.out.println(a[i]+" -------> "+b[i]);
            	
            	
            }
    		
        }
       
    }
    
}