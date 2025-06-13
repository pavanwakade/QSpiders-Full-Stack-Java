/**
 * 
 */
package collectionFramework.collectioninterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 */
public class CollectionInterfaceMethods {

	public static void main(String[] args) {

		Collection<Object> coll = new ArrayList<>();

		// add

		coll.add("String");
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
		// remove using element
		coll1.remove("String");

		System.out.println("remove()" + coll1);

		// remove using index
		((List<String>) coll1).remove(1);

		System.out.println("remove()" + coll1);

	}

}
