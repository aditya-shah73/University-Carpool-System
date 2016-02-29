import java.text.ParseException;
import java.util.Scanner;

/**
 * 
 * @author Group 8
 *
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
					System.out.printf("New User Created!!! - %s: %s  %s\n\n", newUser.getStatus(), newUser.getName(), newUser.getAddress());
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
					System.out.printf("Login Successful!!!\nCurrent User - %s: %s  %s\n\n", user.getStatus(), user.getName(), user.getAddress());
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
