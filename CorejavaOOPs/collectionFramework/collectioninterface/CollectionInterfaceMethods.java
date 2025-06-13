/**
 * 
 */
package collectionFramework.collectioninterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 
 */
public class CollectionInterfaceMethods {

	public static void main(String[] args) {

		Collection<Object> coll = new ArrayList<>();

		//1) add

		coll.add("hello");
		coll.add(1);
		coll.add(true);
		coll.add(1.1);
		coll.add(null);
//		System.out.println("Add()  "+coll);

		Collection<String> coll1 = new ArrayList<>();
		coll1.add("String");
		coll1.add("pavan");
		coll1.add("savan");
		coll1.add("jivan");

		System.out.println("orignal " + coll1);
		//2) remove using element
		coll1.remove("String");
		System.out.println("remove()" + coll1);
		
		//3) remove using index
		((List<String>) coll1).remove(1);
		System.out.println("remove()" + coll1);
		
		
		//4)addAll()
		coll.addAll(coll1);
		System.out.println("addAll  :"+coll);
		
//		5)size()
		System.out.println("size  :"+coll.size());
		
		//6) isEmpty()
		System.out.println("isEmpty()  :"+	coll.isEmpty());
		
		//7)contains
		System.out.println("contains  :"+	coll.contains("hello"));
		
		
		//8)containsAll()
		System.out.println("containsAll  :"+	coll.containsAll(coll1));
		
		
		//9)removeAll()
		coll.removeAll(coll1);
		System.out.println("remove all :"+coll);
		
		
		//10)<T> t[] toArry(t[])
		String [] coll1Strings=coll1.toArray(new String[0]);
		System.out.println(" <T> t[] toArry(t[] ) : "+Arrays.toString(coll1Strings));

//		11) <T>.toArray();
		Object [] obj=coll1.toArray();
		System.out.println(" <T>.toArry()  : "+Arrays.toString(obj));

		
//		12)clear
		System.out.println("Befour clear :"+coll);
		coll.clear();
		System.out.println("After clear  :"+coll);
		
		

	}

}
