package List;

public class Employee {

    int id;

    String name;

    double sal;

    String position;

    public Employee(int id, String name, double sal, String position) {
        id = this.id;
        name = this.name;
        sal = this.sal;
        position = this.position;
    }

    public Employee(Employee emp) {
        this.id = emp.id;
        this.name = emp.name;
        this.sal = emp.sal;
        this.position = emp.position;
    }


}
