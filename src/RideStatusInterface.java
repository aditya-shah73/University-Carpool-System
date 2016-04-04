
public interface RideStatusInterface {

	public void onTheRoad(Rider rider);
	public void arrivedToDestination();
	public void setRideState(RideState rideState);
	public RideState getRideState();
	public boolean isAlreadyOnTheRoad();
	public boolean hasArrived();
}
