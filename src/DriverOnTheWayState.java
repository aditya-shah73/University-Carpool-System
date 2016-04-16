public class DriverOnTheWayState implements RideState{
	private int location;
	public DriverOnTheWayState(int location){
		this.location = location;
	}
	
	@Override
	public void displayCurrentState() {
		System.out.println("Driver is on the way and currently at region: " + this.location + ".");
	}

}