package coupling.Loose;

public class Driver {

	public static void main(String[] args) {

		Ride r = new Ride();

		r.v = new Bike();

		r.startRide();
		
		r.v=new Car();
		
		r.startRide();
		
	}
}
