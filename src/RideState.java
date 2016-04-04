
public interface RideState {
	
	// Below methods sets new states
	public String onTheRoad(Rider rider);
	public String arrivedToDestination();
	
	// Below methods allows user to track the states
	public boolean isAlreadyOnTheRoad();
	public boolean hasArrived();
}
