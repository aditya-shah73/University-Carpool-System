import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author Group 8
 */
public class MainSystem 
{
	private Hashtable<String, User> riderTable = new Hashtable<>(); // table of Rider
	private Hashtable<String, User> driverTable = new Hashtable<>(); // table of Driver

	private void displayMainInterface()
	{
		System.out.println("1. Creating new user.");
		System.out.println("2. Login.");
		System.out.println("3. Exit.");
		System.out.print("Please enter your choice: ");
	}

	private User login()
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
		return returnedUser;
	}

	private User createNewUser()
	{
		Scanner sc = new Scanner(System.in);
		User returnedUser;

		System.out.println("Please enter the following information..");
		System.out.print("Username: ");
		String username = sc.nextLine();

		System.out.print("Full Name: ");
		String fullname = sc.nextLine();

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
			User newRider = new Rider(username, fullname, address, region);
			this.riderTable.put(username, newRider);
			returnedUser = newRider;
			break;
		}
		case 2: // case 2 driver
		{
			User newDriver = new Driver(username,fullname, address, region);
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

	public static void main(String[] args)
	{
		MainSystem mainSystem = new MainSystem();
		Scanner sc = new Scanner(System.in);

		boolean exit = false;
		while(exit == false)
		{
			mainSystem.displayMainInterface();
			int choice = sc.nextInt();

			switch(choice)
			{
			case 1:
			{
				User newUser = mainSystem.createNewUser();
				if(newUser != null)
				{
					System.out.printf("New User Created!!! - %s: %s --- %s\n\n", newUser.getStatus(), newUser.getName(), newUser.getAddress());
				}
				else 
				{
					System.out.println("Error with creating new user");
				}
				break;
			}
			case 2:
			{
				User user = mainSystem.login();

				if(user != null)
				{
					System.out.printf("Login Successful!!!\nCurrent User - %s: %s --- %s\n\n", user.getStatus(), user.getName(), user.getAddress());
				}
				else
				{
					System.out.println("Error with login, please try another username");
				}
				break;
			}
			default:
			{
				System.out.println("Invalid Input, please retry.");
			}
			}
		}
	}
}