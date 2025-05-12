import java.util.Arrays;
class bauncyArray{
public static void main(String[] args) {
	int []a={1,2,3,4,5};
	int []b={10,20,30,40,50};
System.out.println(bouncy(a,b));
	
}

public static int[] bouncy(int a[],int b[]){
	int []c=new int[a.length+b.length];
	for (int i=0;i<a.length;i+1) {
		c[i]=a[i];
		c[i+1]=b[i+1];

	}
	System.out.println(Arrays.toString(c));
	return c;
}
}