package oop.Encapsulation;
import java.util.Scanner;

public class Encapsulation1Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      
        System.out.println("Enter company Location!!!");
        String companyloc = sc.nextLine();

        System.out.println("Employee ID!!!");
        int empno = sc.nextInt();
        
        sc.nextLine();  

        System.out.println("Enter Employee Name!!!");
        String ename = sc.nextLine();

        System.out.println("Enter Employee Location!!!");
        String loc = sc.nextLine();

        System.out.println("Enter Employee Phone No!!!");
        long phoneno = sc.nextLong();
        
        Encapsulation1 e1 = new Encapsulation1(companyloc, empno, ename, loc, phoneno);
        
       
        System.out.println("         Company: " + e1.getCompany());
        System.out.println("Company Location: " + e1.getCompanyLoc());
        System.out.println("Employee Number : " + e1.getEmpNo());
        System.out.println("Employee Name   : " + e1.getEname());
        System.out.println("Phone Number    : " + e1.getPhoneno());
        System.out.println("Location        : " + e1.getLoc());
        System.out.println("-------------------------------------------");
        
        
        System.out.println("Want To Change AntThing yes/no");
        char yes=sc.next().charAt(0);
        
        if (yes=='y'||yes=='Y') {
        	System.out.println("ENTER EMPID!!");
        	int e=sc.nextInt();
        	if (e==(e1.getEmpNo())) {
		
        	System.out.println("Name!!!");
        	System.out.println("location!!!");
        	System.out.println("MO_Number!!!");
        	
         char choice = sc.next().charAt(0);
         sc.nextLine();
		if (choice=='n'||choice=='N') {
			System.out.println("Enter New Name!!");
			String newname=sc.nextLine();
			e1.setEname(newname);
			System.out.println("Name Change with "+newname+" Sucessfully!!!!");
		}
		
		else if (choice=='l'||choice=='L') {
			System.out.println("Enter New Location!!");
			String newloc=sc.nextLine();
			e1.setLoc(newloc);
			System.out.println(" Location Change with "+newloc+" Sucessfully!!!!");
		}
		else if (choice=='m'||choice=='M') {
			System.out.println("Enter New MO_Number!!");
			long newmo=sc.nextLong();
			e1.setPhoneno(newmo);
			System.out.println(" MO_Number Change with "+newmo+" Sucessfully!!!!");
		}
		}
        	 else {
             	System.out.println("Invalid EMPID!!!!");
             }
        }
        else {
        	System.out.println("Thank you!!!!");
        }
        }
        
    }

