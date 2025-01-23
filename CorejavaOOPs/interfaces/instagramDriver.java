package interfaces;

public class instagramDriver {

	public static void main(String[] args) {
		
		instagram i1=new instagranimp2("pavan", "pavanwakade11@gmail.com","123");
		//instagram i2=new instagranimp2("ravan", "ravan11@gmail.com","1234");
		
		
		i1.post("Today is my special day");
		
		i1.like("java");
		
		i1.comment("java", "i love ❤️ you");
		
		i1.message("java", "Hello");
		
		i1.resetpass("123", "pavan@123");
		
	

	}

}
