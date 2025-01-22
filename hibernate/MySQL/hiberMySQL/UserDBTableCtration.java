package hiberMySQL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.sql.Insert;

@Entity
@Table(name = "userDB")
public class UserDBTableCtration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact", nullable = false)
    private long contact;

    @Column(name = "destination")
    private String destination;

    @Column(name = "salary")
    private long salary;

    // Default Constructor
    public UserDBTableCtration() {
        super();
    }

    // Parameterized Constructor
    public UserDBTableCtration(String name, long contact, String destination, long salary) {
        super();
        this.name = name;
        this.contact = contact;
        this.destination = destination;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    
}
