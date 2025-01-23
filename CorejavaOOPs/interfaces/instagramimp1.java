package interfaces;

import java.util.Objects;

public abstract class instagramimp1 implements instagram {
	String name;
	String email;
	String pass;
	
	

public instagramimp1() {
		super();
	}

public instagramimp1(String name, String email, String pass) {
	super();
	this.name = name;
	this.email = email;
	this.pass = pass;
	
	if (equals(this)) {
		
	
	System.out.println("Account created sucesfull!!");
	instagram.create(name);
	}

else
{
System.err.println("Username or email is already created");	
}
}

@Override
public int hashCode() {
	return Objects.hash(email, name, pass);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	instagramimp1 other = (instagramimp1) obj;
	return Objects.equals(email, other.email) && Objects.equals(name, other.name) && Objects.equals(pass, other.pass);
}

@Override
	public void post(String post)
	{
		System.out.println("good morning");
		System.out.println(" posted!!");
	}
@Override
	public void like(String name)
	{
		System.out.println(name+"  ❤️ liked your post");
	}
@Override
	public void comment(String name, String comment)
	{
		System.out.println(comment);
		System.out.println(name+" commented on your post!!");
	}
	
@Override
	public void message(String name,String message) {
		System.out.println(message);
		System.out.println("message sent to "+name);
		
	}


	



	

}
