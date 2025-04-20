package List;

import java.util.Objects;

public class Employee {

    int id;

    String name;

    double sal;

    String position;

    public Employee(int id, String name, double sal, String position) {
        this.id = id;
        this.name = name;
        this.sal = sal;
        this.position = position;
    }


    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", Salary=" + sal + ", Designation=" + position + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee emp = (Employee) obj;
        return id == emp.id && name.equals(emp.name)
                && sal == emp.sal && position.equals(emp.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sal, position);
    }

}

