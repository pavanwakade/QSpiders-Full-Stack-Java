package coupling.Tight;

public class Ride {

	public void rideStarte() {
		
		Bike bike=new Bike();     //if any implimetation of Bike class that affect on Ride class
		
		bike.startBike();
	}
}
