package other;

import java.util.*;

public class ArryOfObject {

    public static void main(String[] args) {

//        Object[] o = {1, "pavan", 10.0};

//        System.out.println(Arrays.toString(o));

        Collection c = new ArrayList();

        for (int i = 0; i <= 50; i++) {

            c.add(i);
        }


        List<Integer> i = new Stack<>();

        i.addAll(c);
//        i.removeAll(i);
//        i.clear();
//        i.remove(i.size()-1);

        System.out.println(i);

        lis();
    }


    public static void lis() {

        List<String> i = Arrays.asList("pavan", "jivan", "savan");
//        i.clear();
        System.out.println(i);
    }
}
