
public interface RideStatusInterface {

	public void setLocation(int location);
	public int getLocation();
	public void setCurrentStatus(RideState status);
	public RideState getCurrentStatus();
	
}
