class jj {

    String name="John Doe";
    int age=30;
    String address="123 Main St";
    String phone="9999999999";
    String email="johan@gmail.com";

    //copy constructor
    jj(jj obj) {
        this.name = obj.name;
        this.age = obj.age;
        this.address = obj.address;
        this.phone = obj.phone;
        this.email = obj.email;
    }
    //default constructor
    jj(){}

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
    public static void main(String[] args) {

        // List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe");
        // List<String> longNames = names.stream().filter(name -> name.length() >
        // 3).collect(Collectors.toList());
        // System.out.println("Names longer than 3 characters: " + longNames);

        // Creating an ArrayList
        // List<String> fruits = new ArrayList<>();
        // fruits.add("Apple");
        // fruits.add("Banana");
        // fruits.add("Apple"); // Duplicates allowed
        // System.out.println(fruits.get(0)); // Outputs: Banana

        // for (String fruit : fruits) {
        //     System.out.println(fruit);

        // }

       
        //copy constructor
        jj obj = new jj();
        obj.display();
        System.out.println("Copying data using default constructor");
        
    }
}