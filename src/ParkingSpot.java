public class ParkingSpot {
	private String parkingNumber;
	private Driver driver;

	public ParkingSpot(String parkingNumber){
		this.parkingNumber = parkingNumber;
	}
	
	public String getParkingNumber(){
		return parkingNumber;
	}
	
	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber = parkingNumber;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public Driver getDriver() {
		return this.driver;
	}
}
