import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
class Countsries{
	public static void main(String[] args) {

		int a[]={1,3,3,4,5};
		int count=0;
		for (int i=0;i<a.length ;i++ ) {
			boolean isDuplicate=false;

			for (int j=i+1;j<a.length ;j++ ) {				
				if (a[i]==a[j]) {
					isDuplicate=true;
					break;
				}
			}
			if (!isDuplicate) {
				count++;
			}
		}
		System.out.println(count);




		Set<Integer> st=new TreeSet<>();
		for (int ii :a ) {
			st.add(ii);
		}

		System.out.println(st.size());

	}
}