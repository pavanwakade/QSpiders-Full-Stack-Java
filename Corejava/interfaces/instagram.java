package interfaces;

public abstract interface instagram {
	
	
	void resetpass(String pass,String newpass);
	
	void post(String post);
	
	void like(String name);
	
	void comment(String name, String comment);
	
	void message(String name,String message);
	
	public static void create(String name) {
		
		System.out.println("welcome "+name+" to our chatting app");
	}

	
	}


