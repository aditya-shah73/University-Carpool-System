
public class OnTheRoadState implements RideState {
	private RideStatusInterface rideStatusInterface;

	public OnTheRoadState(RideStatusInterface rideStatusInterface) {
		this.rideStatusInterface = rideStatusInterface;
	}
	@Override
	public String onTheRoad(Rider rider) {
		rideStatusInterface.setRideState(new ArrivedToDestinationState(rideStatusInterface));
		return "The driver is picking up - " + rider.getName();
	}
	@Override
	public String arrivedToDestination() {
		return "The driver hasn't left yet...";
	}
	@Override
	public boolean isAlreadyOnTheRoad() {
		return true;
	}
	@Override
	public boolean hasArrived() {
		return false;
	}
}
