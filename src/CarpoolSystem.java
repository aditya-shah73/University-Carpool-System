import java.text.ParseException;
import java.util.Scanner;

/**
 * Main Class which runs the system
 * @author Group 8
 */
public class CarpoolSystem 
{
	public static void main(String[] args) throws ParseException
	{
		MainSystem mainSystem = new MainSystem();
		
		// Initialize
		mainSystem.driverTable.put("paul01", new Driver("paul01" , "Paul #1", "Paul #1 address", 8, "8:00AM", "2:00PM", 3));
		mainSystem.driverTable.put("paul02", new Driver("paul02" , "Paul #2", "Paul #2 address", 6, "9:00AM", "3:00PM", 3));
		mainSystem.driverTable.put("paul03", new Driver("paul03" , "Paul #3", "Paul #3 address", 4, "10:00AM", "4:00PM", 3));
		mainSystem.driverTable.put("paul04", new Driver("paul04" , "Paul #4", "Paul #4 address", 3, "11:00AM", "5:00PM", 3));
		mainSystem.driverTable.put("paul05", new Driver("paul05" , "Paul #5", "Paul #5 address", 7, "8:00AM", "4:00PM", 3));
		
		mainSystem.riderTable.put("rav01" , new Rider("rav01" , "Ravuth #1", "Ravuth #1 Address", 2, "8:00AM", "2:00PM"));
		mainSystem.riderTable.put("rav02" , new Rider("rav02" , "Ravuth #2", "Ravuth #2 Address", 3, "9:00AM", "3:00PM"));
		mainSystem.riderTable.put("rav03" , new Rider("rav03" , "Ravuth #3", "Ravuth #3 Address", 4, "10:00AM", "4:00PM"));
		mainSystem.riderTable.put("rav04" , new Rider("rav04" , "Ravuth #4", "Ravuth #4 Address", 5, "11:00AM", "5:00PM"));
		mainSystem.riderTable.put("rav05" , new Rider("rav05" , "Ravuth #5", "Ravuth #5 Address", 6, "8:00AM", "4:00PM"));
		
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while(exit == false)
		{
			System.out.println("***************************\n" +
					"*        MAIN MENU        *\n" +
					"* 1. Create a new user    *\n" +
					"* 2. Display all users    *\n" +
					"* 3. Login                *\n" + 
					"* 4. Exit                 *\n" + 
					"***************************"); 
			System.out.print("Please enter your choice:");
			int choice = in.nextInt();
			switch(choice)
			{
			case 1:
			{
				User newUser = mainSystem.createNewUser();
				if(newUser != null)
				{
					System.out.printf("\nNew User Created! - %s: %s  %s\n\n", newUser.getStatus(), newUser.getName(), newUser.getAddress());
				} 
				else 
				{
					System.out.println("\nError with creating new user");
				}
				break;
			}
			case 2:
			{
				mainSystem.viewAllUser();
				break;
			}
			case 3:
			{
				User user = mainSystem.login();
				
				break;
			}
			case 4:
			{
				System.out.println("Goodbye!!");
				System.exit(0);
			}
			default:
			{
				System.out.println("\nInvalid Input, please retry.");
				break;
			}
			}
		}
	}
}