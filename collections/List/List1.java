package List;

import java.util.ArrayList;
import java.util.List;

public class List1 {

    public static void main(String[] args) {
        List<Employee> list=new ArrayList<Employee>();
        list.add(new Employee(1,"pavan",12000,"developer"));
        list.add(new Employee(2,"ravan",12000,"developer"));

        for(Employee emp: list){

            System.out.println(list);
        }
        }
    }

