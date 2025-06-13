package collectionFramework.collectioninterface;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectionInterfaceMethodsCRUD {

	static Collection<String> coll = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		bufferReadFile();
		addINtoCollection();
		UpdateCollection(coll, "sql", "MySQL");
		RemoveFromCollection(coll, "pavan");
		System.out.println("orignal :" + coll);

	}

	public static Collection<String> addINtoCollection() {
		for (int i = 0; i <= 3; i++) {
			System.out.println("entr String");
			String obj = sc.nextLine();
			coll.add(obj);
			bufferWriteFile(obj);
		}
		System.out.println("insert   :" + coll);
		return coll;
	}

	public static Collection<String> RemoveFromCollection(Collection<String> str, String ss) {

		Iterator<String> it = str.iterator();

		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {

				it.remove();
			}
		}
		System.out.println("Remove   :" + coll);
		return str;
	}

	public static Collection<String> UpdateCollection(Collection<String> str, String ss, String nn) {

		List<String> list = (List<String>) str;
		ListIterator<String> it = list.listIterator();

		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {

				it.set(nn);
			}
		}
		System.out.println("updated  :" + coll);

		return str;

	}

	// Writing to file
    public static void bufferWriteFile(String str) {
        BufferedWriter bf = null;
        try {
        	bf = new BufferedWriter(new FileWriter("CollectionInterfaceMethodsCRUD.txt", true));

            bf.write(str);
            bf.newLine();
            System.out.println("Write successful");
        } catch (IOException e) {
            e.printStackTrace(); // fixed line
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Reading from file
    public static void bufferReadFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("CollectionInterfaceMethodsCRUD.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Process each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close(); // Always close
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}