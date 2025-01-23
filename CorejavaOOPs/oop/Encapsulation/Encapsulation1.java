package oop.Encapsulation;

public class Encapsulation1 {
    String company = "CORELEAF!!!";
    private String companyloc;
    private int empno;
    private String ename;
    private long phoneno;
    private String loc;

    public Encapsulation1() {}
    
    public Encapsulation1(String companyloc, int empno, String ename,String loc, long phoneno ) {
        this.companyloc = companyloc;
        this.empno = empno;
        this.ename = ename;
        this.phoneno = phoneno;
        this.loc = loc;
    }

   
    public String getCompany() {
        return company;
    }

    public String getCompanyLoc() {
        return companyloc;
    }

    public int getEmpNo() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public long getPhoneno() {
        return phoneno;
    }

    public String getLoc() {
        return loc;
    }


    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setPhoneno(long phoneno) {
        this.phoneno = phoneno;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
