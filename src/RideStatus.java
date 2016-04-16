
public class RideStatus implements RideStatusInterface {
	
	private RideState rideState;
	private int driverLocation;

	public RideStatus(RideState state, int location){
		this.rideState = state;
		this.driverLocation = location;
	}
	public RideStatus() {
		this(new DriverNotLeavingYetState(0), 0);
	}
	@Override
	public void setLocation(int location) {
		this.driverLocation = location;
	}

	@Override
	public int getLocation() {
		return driverLocation;
	}

	@Override
	public void setCurrentStatus(RideState status) {
		this.rideState = status;
	}

	@Override
	public RideState getCurrentStatus() {
		return this.rideState;
	}

	

}
