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
					System.out.printf("\n  New User Created! - %s: %s  %s\n\n", newUser.getStatus(), newUser.getName(), newUser.getAddress());
				} 
				else 
				{
					System.out.println("\n  Error with creating new user");
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
				System.out.println("\n Invalid Input, please retry.");
				break;
			}
			}
		}
	}
}