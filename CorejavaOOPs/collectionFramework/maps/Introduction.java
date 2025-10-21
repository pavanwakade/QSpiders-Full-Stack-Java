package collectionFramework.maps;

import java.util.*;

public class Introduction {
    public static void main(String[] args) {
        Map<String,Integer> bankAccNo=new HashMap<>();
        bankAccNo.put("pavan",1);





        Map<Integer,String> students=new LinkedHashMap<>();
        students.put(5,"Pavan");
        students.put(2,"jivan");
        students.put(4,"sp");
        Random random=new Random();
        int key=random.nextInt(100)+1;


//        TreeMap<Integer,String> students1=new TreeMap<>();
//        students1.put(5,"Pavan");
//        students1.put(2,"jivan");
//        students1.put(4,"sp");


        List<Map<Integer,String>> bank=new ArrayList<>();
        bank.add(students1);

//        System.out.println("students "+students);
        System.out.println("bank "+bank);

    }
}
