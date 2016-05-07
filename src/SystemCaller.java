import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The main system for scheduling
 * @author Group 8
 */
public class SystemCaller 
{

	Scanner sc = new Scanner(System.in);
	public static HashMap<String, User> riderTable = new HashMap<>(); // table of Rider
	public static HashMap<String, User> driverTable = new HashMap<>(); // table of Driver
	public static Garage parkingGarage = new Garage();
	public SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	private User returnedUser;
	/*
	 * Default constructor
	 */
	
	public SystemCaller() throws ParseException
	{
		
	}

	/** 
	 * Display Main Carpool Menu after logged in
	 */
	private void displayDriverCarpoolMenu()
	{
		System.out.println("********************************\n" +
				"*       CARPOOL MENU           *\n" +
				"* 1. Carpool from SJSU         *\n" +
				"* 2. Carpool from Home         *\n" +
				"* 3. Display scheduled rides   *\n" +
				"* 4. Get Route                 *\n" +
				"* 5. Update driver status      *\n" +
				"* 6. Manage Parking	       *\n" +
				"* 7. Logout                    *\n" + 
				"*******************************"); 
		System.out.print("Please enter your choice:");
	}

	/** 
	 * Display Main Carpool Menu after logged in
	 */
	private void displayRiderCarpoolMenu()
	{
		System.out.println("********************************\n" +
				"*       CARPOOL MENU           *\n" +
				"* 1. Carpool from SJSU         *\n" +
				"* 2. Carpool from Home         *\n" +
				"* 3. Display scheduled rides   *\n" +
				"* 4. Check Driver status       *\n" +
				"* 5. Pay Driver                *\n" +
				"* 6. Logout                    *\n" + 
				"********************************"); 
		System.out.print("Please enter your choice:");
	}
	
	/** 
	 * Display Parking Menu for driver
	 */
	private void displayDriverParkingMenu()
	{
		System.out.println("********************************\n" +
				"*       PARKING MENU           *\n" +
				"* 1. Find Parking              *\n" +
				"* 2. Exit Parking              *\n" +
				"* 3. View Garage               *\n" +
				"* 4. Exit                      *\n" +
				"*******************************"); 
		System.out.print("Please enter your choice:");
	}
	
	/**
	 *  Display payment menu (rider's interface)
	 */
	private void displayRiderPaymentMenu() {
		System.out.println("********************************\n" +
				"*       PAYMENT MENU           *\n" +
				"* 1. Pay By Cash               *\n" +
				"* 2. Pay By Credit             *\n" +
				"* 3. Exit                      *\n" +
				"********************************"); 
		System.out.print("Please enter your choice:");
	}
	
	private void promptDateForDisplay(User u){
		int choice;
		System.out.println("********************************\n" +
				"*       Please choose a day to display Schedule       *\n" +
				"* 1. Monday               	*\n" +
				"* 2. Tuesday             	*\n" +
				"* 3. Wednesday             	*\n" +
				"* 4. Thursday             	*\n" +
				"* 5. Friday             	*\n" +
				"* 6. Saturday             	*\n" +
				"* 7. Sunday                	*\n" +
				"* 8. Exit             		*\n" +
				"********************************");

		System.out.print("Please enter your choice:");
		choice = sc.nextInt( );
		if(choice != 8){
			u.displayRideFromHome(choice-1);
			u.displayRideFromSchool(choice-1);
		}
		
	}
	
	private void promptDateForPicking( User u, SchemeContext scheme, int choice) throws ParseException{
		int date;
		System.out.println("********************************\n" +
				"*       Date Menu       	*\n" +
				"* 1. Monday              	*\n" +
				"* 2. Tuesday             	*\n" +
				"* 3. Wednesday             	*\n" +
				"* 4. Thursday             	*\n" +
				"* 5. Friday             	*\n" +
				"* 6. Saturday             	*\n" +
				"* 7. Sunday                	*\n" +
				"* 8. Exit             		*\n" +
				"********************************");
		System.out.println("Please choose a date to display schedule.");
		System.out.print("Please enter your choice:");
		date = sc.nextInt( );
	
		if(date != 8){
			scheme.executeScheme(date , u, choice);	
		}
	}
	/**
	 * Display Driver Carpool Menu
	 * @param user
	 * @throws ParseException
	 */
	private void driverCarpoolMenu(User user) throws ParseException{
		int choice;
		int date;
		user.displayNotification();
		Scanner userInput = new Scanner(System.in);
		do {
			displayDriverCarpoolMenu();
			choice = sc.nextInt();
			Driver driver = (Driver) user;
			SchemeContext schemeContext = new SchemeContext();
			switch(choice)
			{
				case 1:
				{
					promptDateForPicking(user, schemeContext, choice);			
					break;
				}
				case 2:
				{
					promptDateForPicking(user, schemeContext, choice);			
					break;
				}
				case 3:
				{
					promptDateForDisplay(driver);
					break;
				}
				case 4:
				{
					displayRoute(driver);
					break;
				}
				case 5:
				{
					updateDriverState(driver);
					break;
				}
				case 6:
				{
					driverParkingMenu(driver);
					break;
				}
				case 7:
					break;
				default:
				{
					System.out.println("Invalid Input. Please try again.");
					System.out.print("Please enter your choice: ");
					choice = sc.nextInt();
					break;
				}
			}
		} 
		while (choice != 7);
	}
	
	
	/**
	 * Display Driver Parking Menu
	 * @param user
	 */
	private void driverParkingMenu(User user) {
		int choice;
		Scanner userInput = new Scanner(System.in);
		do {
			displayDriverParkingMenu();
			choice = sc.nextInt();
			Driver driver = (Driver) user;
			switch(choice)
			{
				case 1:
				{
					if (driver.getParkingSpot() == null) {
						ArrayList<ParkingSpot> theEmptyOnes = parkingGarage.getAllEmpty();
					
						System.out.println("Empty Parking Spots: ");
						for(int i = 0; i < theEmptyOnes.size(); i = i+1){
							System.out.println(theEmptyOnes.get(i).getParkingNumber());
						}
						System.out.print("Select a parking spot: ");
						String chosenParking = userInput.nextLine();
						parkingGarage.selectParking(chosenParking, driver);
						driver.setParkingSpot(chosenParking);
					} else {
						System.out.println("You have already parked.");
					}
									
					break;
				}
				case 2:
				{
					// If user did park, allow them to exit parking spot
					if (driver.getParkingSpot() != null) {
						// Open up the spot 
						driver.setParkingSpot(null);
						parkingGarage.openParking(driver);
					} else {
						// Otherwise they didn't park so they cannot free up a parking spot
						System.out.println("You are currently not parking...");
					}
					break;
				}
				case 3:
				{
					parkingGarage.displayGarage();
					break;
				}
				case 4:
				{
					choice = 4;
					break;
				}
				default:
				{
					System.out.println("Invalid Input. Please try again.");
					System.out.print("Please enter your choice: ");
					choice = sc.nextInt();
					break;
				}
			}
		} 
		while (choice != 4);
	}

	/**
	 * Update Driver Current Location
	 */
	private void updateDriverState(Driver driver){
		System.out.println("1. Will be leaving soon.");
		System.out.println("2. On the way.");
		System.out.println("3. Arrived at the destination.");
		System.out.println("4. Update current location");
		System.out.println("5. Back to previous menu.");
		System.out.println("Please enter your current location: ");
		
		int choice = sc.nextInt();

		while(choice != 5){
			switch(choice){
				case 1:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					driver.setStatus(new DriverNotLeavingYetState(driver.getCurrentLocation()));
					choice = 5;
					driver.displayStatus();
					break;
				}
				case 2:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					RideState rideState = new DriverOnTheWayState(driver.getCurrentLocation());
					driver.setStatus(rideState);
					choice = 5;
					driver.displayStatus();
					break;
				}
				case 3:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					driver.setStatus(new DriverArrivedDestinationState(driver.getCurrentLocation()));
					choice = 5;
					driver.displayStatus();
					break;
				}
				case 4:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					choice = 5;
					driver.displayStatus();
					break;
				}
			
				case 5: 
				{
					break;
				}
				default:
				{
					System.out.println("Invalid Input. Please try again.");
					System.out.print("Please enter your choice: ");
					choice = sc.nextInt();
					break;
				}
			}
		}
		
	}
	/**
	 * Display Route for the Driver
	 * @param Driver
	 */
	private void displayRoute(Driver driver){
		Driver theDriver = driver;
		
		System.out.println("1. Get Route from SJSU.");
		System.out.println("2. Get Route from Home.");
		System.out.println("3. Back to the previous menu");
		System.out.print("Please enter your choice: ");
		int choice = sc.nextInt();
		
		while(choice != 3){
			switch(choice){
				case 1:
				{
					System.out.println("Please pickup riders with the following orders: ");
					HashMap<Integer, User> riderList = theDriver.getRouteFromSchool();
					for(int i = 0; i < riderList.size(); i++){
						System.out.println(">>> " + riderList.get(i).getName() + " at " + riderList.get(i).getAddress() + " for " + format.format(riderList.get(i).getMemberSchedule().get(i).getSchoolTime()));
					}
					choice = 3;
					break;
				}
				case 2:
				{
					System.out.println("Please pickup riders with the following orders: ");
					HashMap<Integer, User> riderList = theDriver.getRouteFromHome();
					for(int i = 0; i < riderList.size(); i++){
						System.out.println(">>> " + riderList.get(i).getName() + " at " + riderList.get(i).getAddress() + " for " + format.format(riderList.get(i).getMemberSchedule().get(i).getHomeTime()));
					}
					choice = 3;
					break;
				}
				case 3:
				{
					choice = 3;
					break;
				}
				default:
				{
					System.out.println("Invalid output. Please enter again.");
					System.out.print("Please enter your choice: ");
					choice = sc.nextInt();
					break;
				}
			}
			
		}
	}
	/**
	 * Check Driver Status: Home - On the way - Arrived
	 * @param rider
	 */
	private void checkDriverStatus(Rider rider){
		Rider theRider = rider;
		int choice;
		
		do {
			System.out.println("1. Check driver status that will pick you up from SJSU.");
			System.out.println("2. Check driver status that will pick you up from Home.");
			System.out.println("3. Back to the previous menu.");
			System.out.print("Please enter your choice: ");
			
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
				{
					if (rider.getDriverFromSchool() != null) {
						rider.getDriverFromSchool().displayStatus();
						
						if (!(rider.getDriverFromSchool().getParkingSpot() == null)) {
							System.out.println("Driver at parking spot: " + rider.getDriverFromSchool().getParkingSpot());
						}

					} else {
						System.out.println("No driver from SJSU...");
						System.out.println();
					}
					break;
				}
				case 2:
				{
					if (rider.getDriverFromHome() != null) {
						rider.getDriverFromHome().displayStatus();
						if (!(rider.getDriverFromSchool().getParkingSpot() != null)) {
							System.out.println("Driver at parking spot: " + rider.getDriverFromHome().getParkingSpot());
						}

					} else {
						System.out.println("No driver from Home...");
						System.out.println();
					}
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					System.out.println("Wrong input. Please enter again.");
					System.out.print("Please enter your choice: ");
					break;
				}
			}
		} while (choice != 3);
		
	}
	private void riderCarpoolMenu(User user) throws ParseException
	{
		int choice;
		user.displayNotification();
		do 
		{
			displayRiderCarpoolMenu();
			choice = sc.nextInt();
			Rider rider = (Rider) user;
			SchemeContext schemeContext;
			switch(choice)
			{
				case 1:
				{
					schemeContext = new SchemeContext();
					promptDateForPicking(rider, schemeContext, choice);	
					/*
					if(rider == null){
						schemeContext = new SchemeContext();
						promptDateForPicking(rider, schemeContext, choice);	
					}
					else
						System.out.println("You already had a driver to pick you up from School.");*/
					break;
				}
				case 2:
				{
					schemeContext = new SchemeContext();
					promptDateForPicking(rider, schemeContext, choice);	
					/*
					if(rider.getDriverFromHome() == null){
						schemeContext = new SchemeContext();
						promptDateForPicking(rider, schemeContext, choice);	
					}
					else
						System.out.println("You already had a driver to pick you up from Home.");
					*/
					break;
				}
				case 3:
				{
					promptDateForDisplay(rider);
					break;
				}
				case 4:
				{
					this.checkDriverStatus(rider);
					break;
				}
				case 5:
				{
					riderPaymentMenu(rider);
					break;
				}
				case 6:
					break;
				default:
				{
					System.out.println("Invalid Input. Please try again.");
					break;
				}
			}
		} 
		while (choice != 6);
	}
	
	private void riderPaymentMenu(Rider rider) {
		int choice;
		int distance;
		
		do {
			displayRiderPaymentMenu();
			choice = sc.nextInt();
			
			switch (choice) 
			{
				case 1:
					rider.payDriverCash();
					break;
				case 2:
					rider.payDriverCredit();
					break;
				case 3:
					System.out.println();
					break;
				default:
					System.out.println("Wrong input. Try again: ");
			}

		} while (choice != 3);
	}
	

	/**
	 * Login function
	 * @return User with correct username/pass. Null if wrong input
	 * @throws ParseException 
	 */
	public User login() throws ParseException
	{
		
		System.out.print("Please enter your username: ");
		String username = sc.next();
		if(this.driverTable.get(username) != null)
		{
			returnedUser = this.driverTable.get(username);
			System.out.printf("\nLogin Successful!!!\nCurrent User - %s: %s  %s\n\n", returnedUser.getStatus(), returnedUser.getName(), returnedUser.getAddress());

			driverCarpoolMenu(returnedUser);
		}
		else if(this.riderTable.get(username) != null)
		{
			returnedUser = this.riderTable.get(username);
			System.out.printf("\nLogin Successful!!!\nCurrent User - %s: %s  %s\n\n", returnedUser.getStatus(), returnedUser.getName(), returnedUser.getAddress());

			riderCarpoolMenu(returnedUser);
		}
		else
		{
			System.out.println("\nError with login, please try another username");
			returnedUser = null;
		}

		return returnedUser;
	}

	public void viewAllUser() 
	{
		System.out.println(" *** LIST OF DRIVERS *** ");
		if (driverTable.isEmpty()) 
		{
			System.out.println("Empty...");
		} 
		else 
		{
			System.out.println("---------------------------------------------------------------------");
			for(Map.Entry<String, User> entry : driverTable.entrySet())
			{
				User theUser = entry.getValue();
				System.out.println("Username: " + theUser.getUsername() + "| Name: " + theUser.getName() + 
						"| Address: " + theUser.getAddress() + "| Region: " + theUser.getRegion());
				System.out.println("\t\t\tMon\tTues\tWed\tThurs\tFri\tSat\tSun");
				System.out.print("  Leave Home at: ");
				for(int i=0;i<7;i++){
				System.out.print("\t" + format.format(theUser.getMemberSchedule().get(i).getHomeTime()));
				}
				System.out.print("\n  Leave School at: ");
				for(int i=0;i<7;i++){
					System.out.print("\t" + format.format(theUser.getMemberSchedule().get(i).getSchoolTime()));
				}
				System.out.println("");
			}
			System.out.println("---------------------------------------------------------------------");
		}
		System.out.println(" *** LIST OF RIDERS ***");
		if (riderTable.isEmpty()) 
		{
			System.out.println("Empty...");
		} 
		else 
		{
			System.out.println("---------------------------------------------------------------------");
			for(Map.Entry<String, User> entry : riderTable.entrySet())
			{
				User theUser = entry.getValue();
				System.out.println("Username: " + theUser.getUsername() + "| Name: " + theUser.getName() + 
						"| Address: " + theUser.getAddress() + "| Region: " + theUser.getRegion());
				System.out.println("\t\t\tMon\tTues\tWed\tThurs\tFri\tSat\tSun");
				System.out.print("  Leave Home at: ");
				for(int i=0;i<7;i++){
				System.out.print("\t" + format.format(theUser.getMemberSchedule().get(i).getHomeTime()));
				}
				System.out.print("\n  Leave School at: ");
				for(int i=0;i<7;i++){
					System.out.print("\t" + format.format(theUser.getMemberSchedule().get(i).getSchoolTime()));
				}
				System.out.println("");
				
			}
			System.out.println("---------------------------------------------------------------------");
		}
	}

	public User createNewUser() throws ParseException
	{return null;}
		/*Scanner sc = new Scanner(System.in);
		User returnedUser;

		System.out.println("Please enter the following information..");
		System.out.print("Username: ");
		String username = sc.nextLine();

		System.out.print("Full Name: ");
		String fullname = sc.nextLine();

		System.out.print("Enter the time you want to leave from your house: ");
		String departFromHome = sc.nextLine();

		System.out.print("Enter the time you want to leave from school: ");
		String departFromSchool = sc.nextLine();

		System.out.print("Address: ");
		String address = sc.nextLine();

		System.out.print("Region: ");
		int region = sc.nextInt();

		System.out.print("Please enter [1] if you are rider, [2] if you are driver: ");
		int status = sc.nextInt();
		int seat = 0;
		if(status == 2)
		{
			System.out.print("Please enter the number of seats available in your car: ");
			seat = sc.nextInt();
		}
		switch(status){}
		
		{
		case 1: // Rider
		{
			User newRider = new Rider(username, fullname, address, region, departFromHome, departFromSchool);
			this.riderTable.put(username, newRider);
			ObserverRideScheme.userArrayList.add(newRider);
			returnedUser = newRider;
			break;
		}
		case 2: // Driver
		{
			User newDriver = new Driver(username,fullname, address, region, departFromHome, departFromSchool, seat);
			this.driverTable.put(username, newDriver);
			ObserverRideScheme.userArrayList.add(newDriver);
			returnedUser = newDriver;
			break;
		}
		default:
		{
			System.out.println("Invalid input. Please try again");
			returnedUser = null;
			break;
		}
		}
		return returnedUser;
		*/
}