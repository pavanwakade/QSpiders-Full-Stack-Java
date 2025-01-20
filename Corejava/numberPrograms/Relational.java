package numberPrograms;
import java.util.Scanner;

class AtmSwitch {
    public static void main(String[] args) {

        float amont = 0;
        float balance = 5000;

        Scanner sc = new Scanner(System.in);
        System.out.println("*******************Welcome***********************");
        System.out.println(" Enter the password");
        int pass = sc.nextInt();
        if (pass == 12345) {
            System.out.println("Enter the Choice");
            System.out.println(" 1.deposit");
            System.out.println(" 2.Withdraw");
            System.out.println(" 3.check balance");
            int ip = sc.nextInt();
            switch (ip) {
                case 1: {
                    System.out.println(" Choice bank type");
                    System.out.println(" 1.Saving.........");
                    System.out.println(" 2.Current.......");
                    int ip1 = sc.nextInt();
                    switch (ip1) {
                        case 1:
                        case 2:
                            System.out.println(" Enter Amount");
                            amont = sc.nextInt();
                            balance += amont;
                            System.out.println("You Want to check balance (y/n)");

                            char ch = sc.next().charAt(0);
                            if (ch == 'y' || ch == 'Y') {
                                System.out.println("Your Current Balance is : " + balance);
                            } else
                                System.out.println("invalid input : ");
                    
                    default :
                        
                        System.out.println("invalid input ");

                        break;
                }
                }

                case 2: {
                    System.out.println("Choice bank type");
                    System.out.println("1.Saving.........");
                    System.out.println("2.Current.......");
                    int ip2 = sc.nextInt();
                    switch (ip2) {
                        case 1:
                        case 2:
                            System.out.println("Enter Amount");
                            amont = sc.nextInt();
                            if (amont <= balance) {

                                balance -= amont;

                                System.out.println("You Want to check balance (y/n)");

                                char ch = sc.next().charAt(0);
                                if (ch == 'y' || ch == 'Y') {
                                    System.out.println("Your Current Balance is : " + balance);
                                } else
                                    System.out.println("invalid input : ");
                            } else
                                System.out.println("insuficient Balance");
                    }
                }
                break;

                case 3:
                    System.out.println("Your Current Balance is : " + balance);
                    break;

                default:
                    System.out.println("invalid input : ");
                    break;
            }
        }
    }
}
