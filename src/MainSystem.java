import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Group 8
 */
public class MainSystem 
{
	private HashMap<String, User> riderTable = new HashMap<>(); // table of Rider
	private HashMap<String, User> driverTable = new HashMap<>(); // table of Driver

	public void displayMainInterface()
	{
		System.out.println("1. Creating new user.");
		System.out.println("2. Login.");
		System.out.println("3. Exit.");
		System.out.print("Please enter your choice: ");
	}

	public User login()
	{
		User returnedUser;
		Scanner sc = new Scanner(System.in);

		System.out.print("Please enter your username: ");
		String username = sc.nextLine();
		if(this.driverTable.get(username) != null)
		{
			returnedUser = this.driverTable.get(username);
		}
		else if(this.riderTable.get(username) != null)
		{
			returnedUser = this.riderTable.get(username);
		}
		else
		{
			returnedUser = null;
		}

		if(returnedUser.getStatus() == "Driver")
		{
			for(Map.Entry<String, User> entry : riderTable.entrySet())
			{
				if( entry.getValue().getRegion() - returnedUser.getRegion() >= 0)
				{
					if(returnedUser.getMemberSchedule().getHomeTime().equals(entry.getValue().getMemberSchedule().getHomeTime()))
					{
						System.out.println("You have to pick up: " + entry.getValue().getName());
					}
				}
			}
		}

		else if(returnedUser.getStatus() == "Rider")
		{
			for(Map.Entry<String, User> entry : driverTable.entrySet())
			{
				if(returnedUser.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(returnedUser.getMemberSchedule().getHomeTime().equals(entry.getValue().getMemberSchedule().getHomeTime()))
					{
						System.out.println("You have to ride with: " + entry.getValue().getName());
					}
				}
			}		
		}
		return returnedUser;
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

		switch(status)
		{
		case 1: // case 1 rider
		{
			User newRider = new Rider(username, fullname, address, region, departFromHome, departFromSchool);
			this.riderTable.put(username, newRider);
			returnedUser = newRider;
			break;
		}
		case 2: // case 2 driver
		{
			User newDriver = new Driver(username,fullname, address, region, departFromHome, departFromSchool);
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