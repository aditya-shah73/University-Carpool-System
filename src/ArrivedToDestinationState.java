
public class ArrivedToDestinationState implements RideState {
	private RideStatusInterface rideStatusInterface;

	public ArrivedToDestinationState(RideStatusInterface rideStatusInterface) {
		this.rideStatusInterface = rideStatusInterface;
	}
	@Override
	public String onTheRoad(Rider rider) {
		return "The driver already left";
	}
	@Override
	public String arrivedToDestination() {
		return "The driver has arrived to the destination";
	}
	@Override
	public boolean isAlreadyOnTheRoad() {
		return true;
	}
	@Override
	public boolean hasArrived() {
		return true;
	}

}
