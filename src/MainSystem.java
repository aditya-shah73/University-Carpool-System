import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The main system for scheduling
 * @author Group 8
 */
public class MainSystem 
{
	Scanner sc = new Scanner(System.in);
	public static HashMap<String, User> riderTable = new HashMap<>(); // table of Rider
	public static HashMap<String, User> driverTable = new HashMap<>(); // table of Driver
	public SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	/*
	 * Default constructor
	 */
	public MainSystem() throws ParseException
	{
		
	}


	private void displayCarpoolMenu()
	{
		System.out.println("********************************\n" +
				"*       CARPOOL MENU           *\n" +
				"* 1. Carpool from SJSU         *\n" +
				"* 2. Carpool from home         *\n" +
				"* 3. Display driving preferences *\n" +
				"* 4. Logout                    *\n" + 
				"*******************************"); 
		System.out.print("Please enter your choice:");
	}

	private void driverCarpoolMenu(User user) throws ParseException{
		int choice;
		user.displayNotification();
		do {
			displayCarpoolMenu();
		choice = sc.nextInt();
		Driver driver = (Driver) user;
		SchemeContext schemeContext = new SchemeContext(driver);
		switch(choice){
			case 1:
				schemeContext.executeScheme(driver, choice);				
				break;
			case 2:
				schemeContext = new SchemeContext(driver);
				schemeContext.executeScheme(driver, choice);
				break;
			case 3:
				driver.displayRideHome();
				driver.displayRideSchool();
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input in Driver menu. Please try again.");
				break;
		}
		} while (choice != 4);
	}
	
	private void riderCarpoolMenu(User user) throws ParseException{
		int choice;
		user.displayNotification();
		do {
		displayCarpoolMenu();

		choice = sc.nextInt();
		Rider rider = (Rider) user;
		SchemeContext schemeContext;
		switch(choice){
			case 1:
				schemeContext = new SchemeContext(rider);
				schemeContext.executeScheme(rider, choice);
				break;
			case 2:
				schemeContext = new SchemeContext(rider);
				schemeContext.executeScheme(rider, choice);
				break;
			case 3:
				rider.displayRideHome();
				rider.displayRideSchool();
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input. Please try again.");
				break;
		}
		} while (choice != 4);
	}
	
	public User login() throws ParseException
	{
		User returnedUser;
		System.out.print("Please enter your username: ");
		String username = sc.next();
		if(this.driverTable.get(username) != null)
		{
			returnedUser = this.driverTable.get(username);
			System.out.printf("\nLogin Successful!!!\nCurrent User - %s: %s  %s\n\n", returnedUser.getStatus(), returnedUser.getName(), returnedUser.getAddress());
			
		}
		else if(this.riderTable.get(username) != null)
		{
			returnedUser = this.riderTable.get(username);
			System.out.printf("\nLogin Successful!!!\nCurrent User - %s: %s  %s\n\n", returnedUser.getStatus(), returnedUser.getName(), returnedUser.getAddress());
			
		}
		else
		{
			System.out.println("\nError with login, please try another username");
			returnedUser = null;
		}
		/*
		// Display Carpool Menu
		if(returnedUser != null){
			displayCarpoolMenu();
		}
		*/
		// Driver display
		if(returnedUser.getStatus().equalsIgnoreCase("Driver")){
			driverCarpoolMenu(returnedUser);
		}
		else{
			riderCarpoolMenu(returnedUser);
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
				System.out.println("Username: " + entry.getValue().getUsername() + " Name: " + entry.getValue().getName() + 
						" Address: " + entry.getValue().getAddress() + " Region: " + entry.getValue().getRegion() +
						" Leaves Home at: " + format.format(entry.getValue().getMemberSchedule().getHomeTime()) + 
						" Leaves SJSU at: " + format.format(entry.getValue().getMemberSchedule().getSchoolTime()));
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
				System.out.println("Username: " + entry.getValue().getUsername() + " Name: " + entry.getValue().getName() + 
						" Address: " + entry.getValue().getAddress() + " Region: " + entry.getValue().getRegion()+
						" Leaves Home at: " + format.format(entry.getValue().getMemberSchedule().getHomeTime()) + 
						" Leaves SJSU at: " + format.format(entry.getValue().getMemberSchedule().getSchoolTime()));
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