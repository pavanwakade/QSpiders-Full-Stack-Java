package ArrayList;

public class User{
int id;
String name;
int amount;

	public User() {
	}
	/**
	 * @param id
	 * @param name
	 * @param amount
	 */
	public User(int id, String name, int amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", amount=" + amount + "]";
	}
//	@Override
//	public int compareTo(User user)
//	{
//		if (this.id>user.id) {
//			return 1;
//		}
//		else if (this.id<user.id) {
//			return -1;
//		}
//		else if (this.amount>user.amount) {
//			return 1;
//		}
//		else if (this.amount<user.amount) {
//			return 1;
//		}
////		return this.name.compareTo(user.name);
//		return 0;
//	}
}