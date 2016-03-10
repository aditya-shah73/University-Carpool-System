import java.text.ParseException;
import java.util.Scanner;

/**
 * Main Class which runs the system
 * @author Group 8
 */
public class SystemTester 
{
	public static void main(String[] args) throws ParseException
	{
		MainSystem mainSystem = new MainSystem();
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