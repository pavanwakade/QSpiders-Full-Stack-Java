package collection.collectioninterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class Collection1 {

//	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Collection col = new ArrayList<>();  
		col.add(1);
		col.add(2);
		col.add(6);
		col.add(1);
		System.out.println(col);   //printed same value   [1, 2, 6, 1]

		Collection tree = new TreeSet<>();   
		tree.add(1);
		tree.add(2);
		tree.add(2);
		tree.add(6);
		tree.add(6);
		tree.add(1);
		System.out.println(tree);   //printed unique value  [1, 2, 6]

		Collection set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(6);
		set.add(1);
		System.out.println(set);    //printed unique value  [1, 2, 6]

	}
}
