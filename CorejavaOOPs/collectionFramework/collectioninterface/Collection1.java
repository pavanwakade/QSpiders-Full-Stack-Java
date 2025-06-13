package collectionFramework.collectioninterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection1 {
	public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("Charlie");
        names.add("Charlie");

        String[] nameObjects = names.toArray(new String[0]);
        int len=nameObjects.length;
        
        for (int i = 0; i < len; i++) {
//			System.out.println(nameObjects[i]);
		}

//        System.out.println(Arrays.toString(nameObjects));
        
        List<Object>kkList=new ArrayList<>();
        kkList.add(1);
        kkList.add("ravan");
        kkList.add("pavan");
        kkList.add(4.4);
        kkList.add(true);
        kkList.add(1);
        kkList.add("ravan");
        System.out.println(kkList);
//        
//        kkList.remove("ravan");
//        System.out.println(kkList);
//
//        kkList.remove(1);
//        System.out.println(kkList);
        
        
        
        
        Object[] ssObjects=kkList.toArray();
        System.out.println(Arrays.toString(ssObjects));
        
        for (int i = 0; i < ssObjects.length; i++) {
        	
			if ( ssObjects[i] instanceof String) {
				
				String ssString= (String) ssObjects[i];
				
				String [] mmStrings=ssString.split("");
				
//				System.out.println( Arrays.toString(mmStrings) );
				String ss = "";
				for (int j = mmStrings.length-1; j >=0; j--) {
					
					 ss+=mmStrings[j];
				}
				
				System.out.println(ss);
		}
        

        // What if we try to store it as String[] directly?
        // String[] nameStrings = (String[]) names.toArray(); // This would compile but throw ClassCastException at runtime!
                                                           // An Object[] cannot be cast to a String[]
        }
        System.out.println(Arrays.toString(ssObjects));
    }
}