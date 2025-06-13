package collectionFramework.collectioninterface;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;


public class collectionInterfaceMethods {

     static  Collection<String> coll=new ArrayList();
     static  Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		addINtoCollection();
	}

	public static void addINtoCollection(){
		for (int i=0;i<=5;i++ ) {
			System.out.println("entr String");
			String obj=sc.nextLine();
		    coll.add(obj);
	}
	System.out.println(coll);

	}



}
