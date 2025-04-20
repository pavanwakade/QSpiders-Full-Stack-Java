package List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListCRUD {

    public static void main(String[] args) {
        List<Employee> emp = new ArrayList<Employee>();
        emp.add(new Employee(1, "Pavan", 1200000, "Developer"));
        emp.add(new Employee(2, "Shashi", 1300000, "Tester"));
        emp.add(new Employee(3, "Anjali", 1100000, "HR"));
        emp.add(new Employee(4, "Ravi", 1250000, "Manager"));
        emp.add(new Employee(5, "Sneha", 1350000, "Developer"));
        emp.add(new Employee(6, "Nikhil", 1280000, "Tester"));
        emp.add(new Employee(7, "Kiran", 1400000, "Developer"));
        emp.add(new Employee(8, "Priya", 1150000, "Support"));
        emp.add(new Employee(9, "Amit", 1500000, "Architect"));
        emp.add(new Employee(10, "Divya", 1320000, "Team Lead"));
        emp.add(new Employee(11, "Sunil", 1180000, "Intern"));

//        remooveEmp(emp, "pavan");
//        updateEmp(emp, "pavan", "Ravan");
        displayEmp(emp);

    }

    public static void displayEmp(List<Employee> emplloyee) {

        for (Employee e : emplloyee) {
            System.out.println(e);
        }
    }

    public static void remooveEmp(List<Employee> employees, String name) {
        boolean removed = false;

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {

            Employee e = iterator.next();

            if (e.name.equalsIgnoreCase(name)) {
                iterator.remove();

                System.out.println(name + " Employee removed");
                removed = true;
                break;
            }

        }
        if (!removed) {
            System.out.println("Not Deleted");
        }
    }


    public static void updateEmp(List<Employee> employees, String name, String newName) {
        boolean removed = false;

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {

            Employee e = iterator.next();

            if (e.name.equalsIgnoreCase(name)) {
                e.name = newName;

                System.out.println(name + " Employee updated");
                removed = true;
                break;
            }

        }
        if (!removed) {
            System.out.println(name + " Not found");
        }
    }


}
