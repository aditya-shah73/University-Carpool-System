
public class DriverArrivedDestinationState implements RideState{
	int location;
	public DriverArrivedDestinationState(int location){
		this.location = location;
	}
	
	@Override
	public void displayCurrentState() {
		System.out.println("Driver has already arrived at destination, region " + this.location + ". Ride ended.");
	}

}
