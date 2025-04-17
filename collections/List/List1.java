package List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class List1 {

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "Pavan", 12000, "Developer"));
        list.add(new Employee(2, "Ravan", 12000, "Developer"));
        list.add(new Employee(3, "Kiran", 15000, "Tester"));
        list.add(new Employee(4, "Sneha", 18000, "Project Manager"));
        list.add(new Employee(5, "Amit", 17000, "Developer"));
        list.add(new Employee(6, "Neha", 16000, "Tester"));
        list.add(new Employee(7, "Sameer", 20000, "Team Lead"));
        list.add(new Employee(8, "Priya", 14000, "Support Engineer"));
        list.add(new Employee(9, "Vikas", 19000, "Architect"));
        list.add(new Employee(10, "Anjali", 13000, "Intern"));
        list.add(new Employee(10, "Anjali", 13000, "Intern"));
        list.add(new Employee(10, "Anjali", 13000, "Intern"));
        list.add(new Employee(10, "Anjali", 13000, "Intern"));
        list.add(new Employee(10, "Anjali", 13000, "Intern"));

        for (Employee emp : list) {
//            if(emp.name.contains("Anjali")) {
//                System.out.println(emp);
//                return;
//            }
            System.out.println(emp);

        }

        System.out.println();

        HashSet<Employee>empl=new HashSet<>(list);

        for (Employee e: list){
            System.out.println(e);
        }
    }
}
