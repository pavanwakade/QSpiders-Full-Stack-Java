package interfaces;
public class instagranimp2 extends instagramimp1{
	
	public instagranimp2() {
		
	}

	public instagranimp2(String name,String email,String pass) {
		super(name,email,pass);
	}


	public void resetpass(String pass, String newpass) {
	    if (this.pass.equals(pass)) {
	        pass = newpass;
	        System.out.println("Password reset successfully!!");
	       // System.out.println(pass);
	    } else {
	    	System.out.println("Current password is incorrect.");
	    }
	}		
}