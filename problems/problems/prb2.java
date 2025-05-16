package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class prb2 {

	public static void main(String[] args) {
		int[] a = { 1, 3, 3, 5, 3, 5 };
		Integer bb=a;

		int count = 0;
//		List<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
		Set<Integer> treeSet = new TreeSet<Integer>();

		// Add elements from array to the TreeSet
		for (int value : a) {
			treeSet.add(value);
		}

		for (Integer integer : treeSet) {
			count++;
		}
		System.out.println(count);
	}
}