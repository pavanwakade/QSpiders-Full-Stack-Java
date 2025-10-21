package collectionFramework.maps;

import java.util.*;

public class Introduction {
    public static void main(String[] args) {
        Map<String,Integer> bankAccNo=new HashMap<>();
        bankAccNo.put("pavan",1);





        Map<Integer,String> students=new HashMap<>();
        students.put(5,"Pavan");
        students.put(2,"jivan");
        students.put(4,"sp");


        TreeMap<Integer,String> students1=new TreeMap<>();
        students.put(5,"Pavan");
        students.put(2,"jivan");
        students.put(4,"sp");


        List<Map<Integer,String>> bank=new ArrayList<>();
        bank.add(students1);
        bank.add(students);

//        System.out.println("students "+students);
        System.out.println("bank "+bank);


        Random random=new Random();
//        long key = Math.abs(random.nextLong() * 1_000_000_000_00L) * 2L;

//        long key = Math.abs(random.nextLong() * 1_000_000_000_000L);

//        System.out.println(key);

    }
}
