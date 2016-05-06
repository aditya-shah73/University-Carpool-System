import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * Main Class which runs the system
 * @author Group 8
 */
public class CarpoolSystem 
{
	public static void main(String[] args) throws ParseException
	{
		SystemCaller mainSystem = new SystemCaller();
		ObserverRideScheme observerRideScheme = new ObserverRideScheme();

		// initialize users
		initializeUser(mainSystem, observerRideScheme);
		
		mainSystem.parkingGarage.generateParkingSpot();
		mainSystem.parkingGarage.generateGarage();
				
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
						System.out.println("\nError with creating new user. Please try again. \n");
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
					while(user == null){
						System.out.println("Please login again.");
						user = mainSystem.login();
					}
					break;
				}
				case 4:
				{
					System.out.println("Goodbye!!");
					exit = true;
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
	private static void initializeUser(SystemCaller mainSystem, ObserverRideScheme observerRideScheme) throws ParseException{
		// Initialize
		ArrayList<String> departHome1 = new ArrayList<>();
		departHome1.add("8:00AM");
		departHome1.add("9:00AM");
		departHome1.add("8:00AM");
		departHome1.add("9:00AM");
		departHome1.add("10:00AM");
		departHome1.add("11:00AM");
		departHome1.add("11:00AM");
		
		ArrayList<String> departHome2 = new ArrayList<>();
		departHome2.add("9:00AM");
		departHome2.add("11:00AM");
		departHome2.add("9:00AM");
		departHome2.add("11:00AM");
		departHome2.add("8:00AM");
		departHome2.add("10:00AM");
		departHome2.add("10:00AM");
		
		ArrayList<String> departHome3 = new ArrayList<>();
		departHome3.add("8:30AM");
		departHome3.add("9:30AM");
		departHome3.add("8:30AM");
		departHome3.add("9:30AM");
		departHome3.add("11:00AM");
		departHome3.add("10:00AM");
		departHome3.add("10:00AM");
		
		ArrayList<String> departSchool1= new ArrayList<>();
		departSchool1.add("1:00PM");
		departSchool1.add("3:00PM");
		departSchool1.add("1:00PM");
		departSchool1.add("3:00PM");
		departSchool1.add("4:00PM");
		departSchool1.add("5:00PM");
		departSchool1.add("5:00PM");
		
		ArrayList<String> departSchool2 = new ArrayList<>();
		departSchool2.add("2:00PM");
		departSchool2.add("3:00PM");
		departSchool2.add("2:00PM");
		departSchool2.add("3:00PM");
		departSchool2.add("5:00PM");
		departSchool2.add("6:00PM");
		departSchool2.add("6:00PM");
		
		ArrayList<String> departSchool3 = new ArrayList<>();
		departSchool3.add("2:00PM");
		departSchool3.add("4:00PM");
		departSchool3.add("2:00PM");
		departSchool3.add("4:00PM");
		departSchool3.add("6:00PM");
		departSchool3.add("5:00PM");
		departSchool3.add("5:00PM");
		
		// Driver List
		Driver driver1 = new Driver("paul01", "Paul Phan", "198 Garden Bing Cir, CA 95131", 10, departHome1, departSchool1, 6);
		mainSystem.driverTable.put("paul01", driver1);
		observerRideScheme.addObserver(driver1);
		
		Driver driver2 = new Driver("linh01", "Linh Phan", "789 Kirk Ave, CA 95122", 8, departHome2, departSchool2, 3);
		mainSystem.driverTable.put("linh01", driver2);
		observerRideScheme.addObserver(driver2);
		
		Driver driver3 = new Driver("brad01", "Brad Fujizawa ", "175 Carrie St, CA 95112", 9, departHome3, departSchool3, 3);
		mainSystem.driverTable.put("brad01", driver3);
		observerRideScheme.addObserver(driver3);
		
		Driver driver4 = new Driver("john01", "John Perez", "1025 E San Antonio St", 8, departHome1, departSchool3, 3);
		mainSystem.driverTable.put("john01", driver4);
		observerRideScheme.addObserver(driver4);
		
		Driver driver5 = new Driver("chris01", "Chris Vargas", "127 Bellevue Ave, CA 95122", 7, departHome2, departSchool2, 3);
		mainSystem.driverTable.put("chris01", driver5);
		observerRideScheme.addObserver(driver5);

		// ****** Rider List  **********
		Rider rider1 = new Rider("steven01", "Steven Rentie", "1472 Bahama Way, CA 95112", 4, departHome1, departSchool1);
		mainSystem.riderTable.put("steven01" , rider1);
		observerRideScheme.addObserver(rider1);
		
		Rider rider2 = new Rider("rav01", "Ravuth Long", "999 McLaughin Ave, CA 95133", 3, departHome2, departSchool1);
		mainSystem.riderTable.put("rav01" , rider2);
		observerRideScheme.addObserver(rider2);
		
		Rider rider3 = new Rider("jack01", "Jack Caravayo", "1775 S King Rd, CA 95112", 2, departHome3, departSchool1);
		mainSystem.riderTable.put("jack01" , rider3);
		observerRideScheme.addObserver(rider3);
		
		Rider rider4 = new Rider("elsa01", "Elsa Mojica", "555 McLaughin Ave, 95112", 1, departHome3, departSchool2);
		mainSystem.riderTable.put("elsa01" , rider4);
		observerRideScheme.addObserver(rider4);
		
		Rider rider5 = new Rider("olivia01", "Olivia Ramos", "1826 La Porte Way, CA 95112", 5, departHome1, departSchool3);
		mainSystem.riderTable.put("olivia01" , rider5);
		observerRideScheme.addObserver(rider5);
		
		Rider rider6 = new Rider("maila01", "Maila Kaelyn", "245 Stedgehone Way, CA 95115", 7, departHome2, departSchool2);
		mainSystem.riderTable.put("maila01" , rider6);
		observerRideScheme.addObserver(rider6);
		
		Rider rider7 = new Rider("ramiro01", "Ramiro Cruz", "1472 Leeward Ave, CA 95112", 6, departHome1, departSchool3);
		mainSystem.riderTable.put("ramiro01" , rider7);
		observerRideScheme.addObserver(rider7);
		
		Rider rider8 = new Rider("mike01", "Mie Caballero", "950 S 10th St, CA 95112", 4, departHome1, departSchool2);
		mainSystem.riderTable.put("mike01" , rider8);
		observerRideScheme.addObserver(rider8);
		
		Rider rider9 = new Rider("jessica01", "Jessica Covarru", "2131 Simon Ave, CA 95112", 2, departHome3, departSchool2);
		mainSystem.riderTable.put("jessica01" , rider9);
		observerRideScheme.addObserver(rider9);
		
		Rider rider10 = new Rider("lopez01", "Lopez Muricano", "1370 Cathay Dr, CA 95112", 1, departHome2, departSchool1);
		mainSystem.riderTable.put("lopez01" , rider10);
		observerRideScheme.addObserver(rider10);
		
		Rider rider11 = new Rider("martha01", "Martha Vera", "1538 Foley Ave, CA 95112", 5, departHome1, departSchool1);
		mainSystem.riderTable.put("martha01" , rider11);
		observerRideScheme.addObserver(rider11);
		
		Rider rider12 = new Rider("andrew01", "Andrew Andrea", "2390 Lucrecia Ave, CA 95112", 7, departHome3, departSchool3);
		mainSystem.riderTable.put("andrew01" , rider12);
		observerRideScheme.addObserver(rider12);
		
		Rider rider13 = new Rider("felina01", "Felina Fujilia", "2603 Brahms Way, CA 95162", 6, departHome2, departSchool2);
		mainSystem.riderTable.put("felina01" , rider13);
		observerRideScheme.addObserver(rider13);
		
		Rider rider14 = new Rider("shu01", "Shu Setino", "1666 Scotty Ave, CA 95112", 7, departHome1, departSchool3);
		mainSystem.riderTable.put("shu01" , rider14);
		observerRideScheme.addObserver(rider14);
		
		Rider rider15 = new Rider("sammy01", "Sammy Mitsubishi", "1867 S White Rd, CA 95122", 4, departHome2, departSchool1);
		mainSystem.riderTable.put("sammy01" , rider15);
		observerRideScheme.addObserver(rider15);
	}
}