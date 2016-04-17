import java.util.Map.Entry;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Garage {
	//create a tree map with parking spot as key and boolean as value
	private HashMap<ParkingSpot, Boolean> map = new HashMap<ParkingSpot, Boolean>();
	private ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
	
	public Garage(){
	}
	
	public void generateParkingSpot() {
		// Generate parking spot 
		// A1-A3, B1-B3, C1-C3
		String alphabet[] = {"A", "B", "C"};
		for(int i = 0; i < alphabet.length; i++){
			for(int j = 1; j <= 3; j++){
				String temp = alphabet[i] + String.valueOf(j);
				ParkingSpot temp1 = new ParkingSpot(temp);
				parkingSpots.add(temp1);
			}
		}
	}
	
	public void generateGarage() {
		// Set all parking spots to be opened 
		for(int i = 0; i < parkingSpots.size(); i++){
			ParkingSpot temp = parkingSpots.get(i);
			map.put(temp, false);
		}
	}
	
	public boolean isEmpty(ParkingSpot key){
		if(map.get(key) == false){
			return true;
		}
		return false;
	}
	
	// Change selected spot to true, meaning the spot is occupied
	public void selectParking(String parkingSpot, Driver driver) {
	
		Set<Entry<ParkingSpot, Boolean>> set = map.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext()){
			Map.Entry m = (Map.Entry) iterator.next();
			ParkingSpot spot = (ParkingSpot) m.getKey();
			if (spot.getParkingNumber().equals(parkingSpot)){
				map.replace(spot, true);
				spot.setDriver(driver);
				System.out.println("You are now parking at parking spot: " + parkingSpot);
			}
		}
	}
	
	// Driver is done parking, set spot to open
	public void openParking(Driver driver) {
		
		Set<Entry<ParkingSpot, Boolean>> set = map.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext()){
			Map.Entry m = (Map.Entry) iterator.next();
			ParkingSpot spot = (ParkingSpot) m.getKey();
			if (spot.getDriver() != null) {
				if (spot.getDriver().equals(driver)){
					map.replace(spot, false);
					spot.setDriver(null);
					System.out.println("You are no longer parking at the current spot");

				}
			}
		}
	}
	
	/**
	 * Adds all the empty spots into one array list 
	 * @return an array list with the names of all the empty spots 
	 */
	public ArrayList<ParkingSpot> getAllEmpty(){
		ArrayList<ParkingSpot> allEmpty = new ArrayList<ParkingSpot>();

		Set<Entry<ParkingSpot, Boolean>> set = map.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext()){
			Map.Entry m = (Map.Entry) iterator.next();
			if ((Boolean) m.getValue() == false){
				allEmpty.add((ParkingSpot) m.getKey());
			}
		}
		return allEmpty;
	}
	
	// Display every spot in the garage
	public void displayGarage() {
		ArrayList<ParkingSpot> allEmpty = new ArrayList<ParkingSpot>();

		Set<Entry<ParkingSpot, Boolean>> set = map.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext()){
			Map.Entry m = (Map.Entry) iterator.next();
			ParkingSpot spot = (ParkingSpot) m.getKey();
			
			System.out.print(spot.getParkingNumber());
			if (spot.getDriver() != null) {
				System.out.print(" occupied by driver: " + spot.getDriver().getName());
			}
			System.out.println();
		}
	}
}
