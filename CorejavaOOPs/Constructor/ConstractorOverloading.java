package Constructor;
class ConstractorOverloading
{
	
	String form="Login Form";
	String name;
	String pass;
	String dob;
	long moNo;
	
	ConstractorOverloading()
	{
		
	}
	
	ConstractorOverloading(String name,String pass)
	{
		this.name=name;
		this.pass=pass;
	}
	
	ConstractorOverloading(String name,String pass,String dob,long moNo)
	{
		this.name=name;
		this.pass=pass;
		
		this.dob=dob;
		this.moNo=moNo;
	}
	
	public void displayForm()
	{
		System.out.println("Form :"+form);
		System.out.println("Name :"+name);
		System.out.println("Pass :"+pass);
		System.out.println("dob  :"+dob);
		System.out.println("MO.NO:"+moNo);
		System.out.println("---------------------------");
	}
}