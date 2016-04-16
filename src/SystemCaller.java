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
				"* 6. Logout                    *\n" + 
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
				"* 5. Logout                    *\n" + 
				"*******************************"); 
		System.out.print("Please enter your choice:");
	}
	/**
	 * Display Driver Carpool Menu
	 * @param user
	 * @throws ParseException
	 */
	private void driverCarpoolMenu(User user) throws ParseException{
		int choice;
		user.displayNotification();
		do {
			displayDriverCarpoolMenu();
			choice = sc.nextInt();
			Driver driver = (Driver) user;
			SchemeContext schemeContext = new SchemeContext(driver);
			switch(choice)
			{
				case 1:
				{
					schemeContext.executeScheme(driver, choice);				
					break;
				}
				case 2:
				{
					schemeContext.executeScheme(driver, choice);
					break;
				}
				case 3:
				{
					driver.displayRideFromHome();
					driver.displayRideFromSchool();
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
					choice = 6;
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
		while (choice != 6);
	}

	/**
	 * Update Driver Current Location
	 */
	private void updateDriverState(Driver driver){
		System.out.println("1. On the way.");
		System.out.println("2. Arrived at the destination.");
		System.out.println("3. Will be leaving soon.");
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
					RideState rideState = new DriverOnTheWayState(driver.getCurrentLocation());
					driver.setStatus(rideState);
					choice = 5;

					break;
				}
				case 2:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					driver.setStatus(new DriverArrivedDestinationState(driver.getCurrentLocation()));
					choice = 5;

					break;
				}
				case 3:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					driver.setStatus(new DriverNotLeavingYetState(driver.getCurrentLocation()));
					choice = 5;

					break;
				}
				case 4:
				{
					System.out.print("Current Location: ");
					driver.setCurrentLocation(sc.nextInt());
					choice = 5;
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
					ArrayList<User> riderList = theDriver.getRouteFromSchool();
					for(int i = 0; i < riderList.size(); i++){
						System.out.println(">>> " + riderList.get(i).getName() + " at " + riderList.get(i).getAddress());
					}
					choice = 3;
					break;
				}
				case 2:
				{
					System.out.println("Please pickup riders with the following orders: ");
					ArrayList<User> riderList = theDriver.getRouteFromHome();
					for(int i = 0; i < riderList.size(); i++){
						System.out.println(">>> " + riderList.get(i).getName() + " at " + riderList.get(i).getAddress());
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
		
		System.out.println("1. Check driver status that will pick you up from SJSU.");
		System.out.println("2. Check driver status that will pick you up from Home.");
		System.out.println("3. Back to the previous menu.");
		System.out.print("Please enter your choice: ");
		
		int choice = sc.nextInt();
		
		while(choice != 3){
			switch(choice){
				case 1:
				{
					rider.getDriverFromSchool().displayStatus();
					choice = 3;
					break;
				}
				case 2:
				{
					rider.getDriverFromHome().displayStatus();
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
					System.out.println("Wrong input. Please enter again.");
					System.out.print("Please enter your choice: ");
					choice = sc.nextInt();
					break;
				}
			}
		}
		
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
					if(rider.getDriverFromSchool() == null){
						schemeContext = new SchemeContext(rider);
						schemeContext.executeScheme(rider, choice);
					}
					else
						System.out.println("You already had a driver to pick you up from School.");
					break;
				}
				case 2:
				{
					if(rider.getDriverFromHome() == null){
						schemeContext = new SchemeContext(rider);
						schemeContext.executeScheme(rider, choice);
					}
					else
						System.out.println("You already had a driver to pick you up from Home.");
				
					break;
				}
				case 3:
				{
					rider.displayRideFromHome();
					rider.displayRideFromSchool();
					break;
				}
				case 4:
				{
					this.checkDriverStatus(rider);
					break;
				}
				case 5:
				{
					choice = 5;
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
		while (choice != 5);
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
		System.out.println("LIST OF DRIVERS");
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
				System.out.println("Username: " + theUser.getUsername() + " Name: " + theUser.getName() + 
						" Address: " + theUser.getAddress() + " Region: " + theUser.getRegion() +
						" Leaves Home at: " + format.format(theUser.getMemberSchedule().getHomeTime()) + 
						" Leaves SJSU at: " + format.format(theUser.getMemberSchedule().getSchoolTime()));
			}
			System.out.println("---------------------------------------------------------------------");
		}
		System.out.println("LIST OF RIDERS");
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
				System.out.println("Username: " + theUser.getUsername() + " Name: " + theUser.getName() + 
						" Address: " + theUser.getAddress() + " Region: " + theUser.getRegion()+
						" Leaves Home at: " + format.format(theUser.getMemberSchedule().getHomeTime()) + 
						" Leaves SJSU at: " + format.format(theUser.getMemberSchedule().getSchoolTime()));
			}
			System.out.println("---------------------------------------------------------------------");
		}
	}

	public User createNewUser() throws ParseException
	{
		Scanner sc = new Scanner(System.in);
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
		switch(status)
		{
		case 1: // Rider
		{
			User newRider = new Rider(username, fullname, address, region, departFromHome, departFromSchool);
			this.riderTable.put(username, newRider);
			returnedUser = newRider;
			break;
		}
		case 2: // Driver
		{
			User newDriver = new Driver(username,fullname, address, region, departFromHome, departFromSchool, seat);
			this.driverTable.put(username, newDriver);
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
	}
}