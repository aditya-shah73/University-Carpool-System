
public class RideStatus implements RideStatusInterface {
	private RideState rideState;

	@Override
	public void onTheRoad(Rider rider) {
		System.out.println(rideState.onTheRoad(rider));
	}

	@Override
	public void arrivedToDestination() {
		System.out.println(rideState.arrivedToDestination());
	}

	@Override
	public void setRideState(RideState rideState) {
		this.rideState = rideState;
	}

	@Override
	public RideState getRideState() {
		return rideState;
	}

	@Override
	public boolean isAlreadyOnTheRoad() {
		return rideState.isAlreadyOnTheRoad();
	}

	@Override
	public boolean hasArrived() {
		return rideState.hasArrived();
	}
	
	

}
