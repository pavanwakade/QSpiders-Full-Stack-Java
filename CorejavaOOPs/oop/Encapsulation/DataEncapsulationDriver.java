package oop.Encapsulation;

public class DataEncapsulationDriver {
    /**
     * The main method initializes a DataEncapsulation object with specific bank details,
     * displays its data, and demonstrates getter and setter methods for accessing
     * and modifying the balance.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        DataEncapsulation d1=new DataEncapsulation("SBI","Pavan","123",99112233445566l,90000,8317277608l);
        d1.displayDetails();
        System.out.println("Bank Name   : "+d1.getBankName());
        System.out.println("Holder Name : "+d1.getHolderName());
        System.out.println("Account No  : "+d1.getAccNo());
        System.out.println("Bank balance: "+d1.getBalance());
        System.out.println("Contact NO  : "+d1.getConNo());
        d1.setBalance(5000,"123");
        System.out.println("Bank balance: "+d1.getBalance());
    }
}
