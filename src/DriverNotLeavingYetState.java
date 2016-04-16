
public class DriverNotLeavingYetState implements RideState{
	private int location;
	public DriverNotLeavingYetState(int location){
		this.location = location;
	}
	
	@Override
	public void displayCurrentState() {
		System.out.println("Driver has not left yet and will depart at region: " + this.location + ".");
	}

}
