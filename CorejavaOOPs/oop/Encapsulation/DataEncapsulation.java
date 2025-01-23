package oop.Encapsulation;

// DataEncapsulation1.java
public class DataEncapsulation {
    private String bankName;
    private String holderName;
    private String pin = "123";
    private long accNo;
    private double balance;
    private long conNo;

    public DataEncapsulation(String bankName, String holderName, String pin, long accNo, double balance, long conNo) {
        this.bankName = bankName;
        this.holderName = holderName;
        this.accNo = accNo;
        this.balance = balance;
        this.conNo = conNo;
    }

    public String getBankName() {
        return bankName;
    }

    public String getHolderName() {
        return holderName;
    }

    public long getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }

    public long getConNo() {
        return conNo;
    }

    public void setBalance(double balance, String pin) {
        if (pin.equals(this.pin)) {
            this.balance = balance;
            System.out.println("Balance Changed Successfully");
        } else {
            System.out.println("Invalid Pin");
        }
    }

    public void displayDetails() {
        System.out.println("Bank Name   : " + bankName);
        System.out.println("Holder Name : " + holderName);
        System.out.println("Account No  : " + accNo);
        System.out.println("Bank Balance: " + balance);
        System.out.println("Contact No  : " + conNo);
    }
}
