import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	
	public MainSystem() throws ParseException{
		riderTable.put("paul1", new Rider("paul1 <username>", "paul1 <name>", "123a", 5, "8:00", "2:00"));
		driverTable.put("paul2", new Driver("paul2 <username>", "paul2 <name> ", "123b", 7, "8:00", "2:00", 3));
	}
	public void displayMainInterface()
	{
		System.out.println("***************************\n" +
				"*        MAIN MENU        *\n" +
				"* 1. Creating new user    *\n" +
				"* 2. Display all user     *\n" +
				"* 3. Login                *\n" + 
				"* 4. Exit                 *\n" + 
				"***************************"); 
		System.out.print("  Please enter your choice:");
	}

	private void displayCarpoolService()
	{
		System.out.println("***************************\n" +
				"*       CARPOOL MENU      *\n" +
				"* 1. Carpool from home    *\n" +
				"* 2. Carpool from SJSU    *\n" +
				"* 3. Exit                 *\n" + 
				"***************************"); 
		System.out.print("  Please enter your choice:");
	}

	public User login() throws ParseException
	{
		User returnedUser;
		Scanner sc = new Scanner(System.in);

		System.out.print("  Please enter your username: ");
		String username = sc.nextLine();
		if(this.driverTable.get(username) != null)
		{
			returnedUser = this.driverTable.get(username);
			System.out.printf("\n  Login Successful!!!\nCurrent User - %s: %s  %s\n\n", returnedUser.getStatus(), returnedUser.getName(), returnedUser.getAddress());
		}
		else if(this.riderTable.get(username) != null)
		{
			returnedUser = this.riderTable.get(username);
			System.out.printf("\n  Login Successful!!!\nCurrent User - %s: %s  %s\n\n", returnedUser.getStatus(), returnedUser.getName(), returnedUser.getAddress());

		}
		else
		{
			System.out.println("\n  Error with login, please try another username");
			returnedUser = null;
		}

		int choice;
		do 
		{
			displayCarpoolService();
			choice = sc.nextInt();

			switch (choice) 
			{
			case 1:
				if(returnedUser.getStatus() == "Driver")
				{
			 
					for(Map.Entry<String, User> entry : riderTable.entrySet())
					{
						
							if(entry.getValue().isAvailable()){
							if(returnedUser.getRegion() - entry.getValue().getRegion() >= 0)
							{
								if(returnedUser.getMemberSchedule().getSchoolTime().equals(entry.getValue().getMemberSchedule().getSchoolTime()))
								{
									
									System.out.println("    You may pick up: " + entry.getValue().getName() + "Username: " + entry.getValue().getUsername());
								}
								
								/*
								while(!usernameChoice.equalsIgnoreCase("0")){
									System.out.println("Enter username to pickup, [0] to exit: ");
									usernameChoice = sc.nextLine();
									
									newRider =  this.riderTable.get(usernameChoice);
									
									newRider.notAvailable();
									returnedUser.addRider(newRider);
								}
								*/
							}
						}
					}
					System.out.print("Enter username to pickup, [0] to exit: ");
					sc.nextLine();
					String usernameChoice = sc.nextLine();
					
					
					User newRider =  this.riderTable.get(usernameChoice);
					
					if(newRider != null){
						newRider.notAvailable();
						returnedUser.addRider(newRider);
						System.out.println("Done, ride with: " +newRider.getName());
					}
				}
				else if(returnedUser.getStatus() == "Rider")
				{
					for(Map.Entry<String, User> entry : driverTable.entrySet())
					{
						if(entry.getValue().getRegion() - returnedUser.getRegion() >= 0)
						{
							if(returnedUser.getMemberSchedule().getHomeTime().equals(entry.getValue().getMemberSchedule().getHomeTime()))
							{
								System.out.println("    You may ride with: " + entry.getValue().getName() + " at " + format.format(returnedUser.getMemberSchedule().getHomeTime()));
							}
						}
					}		
				}
				System.out.println();
				break;
			case 2:
				if(returnedUser.getStatus() == "Driver")
				{
			 
					for(Map.Entry<String, User> entry : riderTable.entrySet())
					{
						
					
						if(entry.getValue().isAvailable()){
							if(returnedUser.getRegion() - entry.getValue().getRegion() >= 0)
							{
								if(returnedUser.getMemberSchedule().getSchoolTime().equals(entry.getValue().getMemberSchedule().getSchoolTime()))
								{
									
									System.out.println("    You may pick up: " + entry.getValue().getName() + " at " + format.format(returnedUser.getMemberSchedule().getSchoolTime() 
											+ "Username: " + entry.getValue().getUsername()));
								}
								String usernameChoice = "";
								while(!usernameChoice.equals("0")){
									System.out.println("Enter username to pickup, [0] to exit: ");
									usernameChoice = sc.nextLine();
									
									User newRider =  this.riderTable.get(usernameChoice);
									
									newRider.notAvailable();
									returnedUser.addRider(newRider);
								}
								
							}
						}
						}
						
				}
				else if(returnedUser.getStatus() == "Rider")
				{
					for(Map.Entry<String, User> entry : driverTable.entrySet())
					{
						if(entry.getValue().getRegion() - returnedUser.getRegion() >= 0)
						{
							if(returnedUser.getMemberSchedule().getSchoolTime().equals(entry.getValue().getMemberSchedule().getSchoolTime()))
							{
								System.out.println("    You may ride with: " + entry.getValue().getName() + " at " + format.format(returnedUser.getMemberSchedule().getSchoolTime()));
							}
						}
					}		
				}
				System.out.println();
				break;
			case 3:
				System.out.println();
				break;
			default:
				System.out.println("Wrong input...");
			}
		} 
		while (choice != 3);
		return returnedUser;
	}

	public void viewAllUser() 
	{
		System.out.println("  LIST OF DRIVERS");
		if (driverTable.isEmpty()) 
		{
			System.out.println("  Empty...");
		} 
		else 
		{
			System.out.println("  ---------------------------------------------------------------------");
			for(Map.Entry<String, User> entry : driverTable.entrySet())
			{
				System.out.println("  Username: " + entry.getValue().getUsername() + " Name: " + entry.getValue().getName() + 
						" Address: " + entry.getValue().getAddress() + " Region: " + entry.getValue().getRegion() +
						" Leaves Home at: " + format.format(entry.getValue().getMemberSchedule().getHomeTime()) + 
						" Leaves SJSU at: " + format.format(entry.getValue().getMemberSchedule().getSchoolTime()));
			}
			System.out.println("  ---------------------------------------------------------------------");
		}
		System.out.println("  LIST OF RIDERS");
		if (riderTable.isEmpty()) 
		{
			System.out.println("  Empty...");
		} 
		else 
		{
			System.out.println("  ---------------------------------------------------------------------");
			for(Map.Entry<String, User> entry : riderTable.entrySet())
			{
				System.out.println("  Username: " + entry.getValue().getUsername() + " Name: " + entry.getValue().getName() + 
						" Address: " + entry.getValue().getAddress() + " Region: " + entry.getValue().getRegion()+
						" Leaves Home at: " + format.format(entry.getValue().getMemberSchedule().getHomeTime()) + 
						" Leaves SJSU at: " + format.format(entry.getValue().getMemberSchedule().getSchoolTime()));
			}
			System.out.println("  ---------------------------------------------------------------------");
		}
	}

	public User createNewUser() throws ParseException
	{
		Scanner sc = new Scanner(System.in);
		User returnedUser;

		System.out.println("  Please enter the following information..");
		System.out.print("  Username: ");
		String username = sc.nextLine();

		System.out.print("  Full Name: ");
		String fullname = sc.nextLine();

		System.out.print("  Enter the time you want to leave from your house: ");
		String departFromHome = sc.nextLine();

		System.out.print("  Enter the time you want to leave from school: ");
		String departFromSchool = sc.nextLine();

		System.out.print("  Address: ");
		String address = sc.nextLine();

		System.out.print("  Region: ");
		int region = sc.nextInt();

		System.out.print("  Please enter [1] if you are rider, [2] if you are driver: ");
		int status = sc.nextInt();
		int seat = 0;
		if(status == 2){
			System.out.print("Please enter available seat: ");
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