import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

public class DriverPickStrategy extends ObserverRideScheme implements RideScheduleScheme {

	public DriverPickStrategy() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}

	SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	private Scanner sc = new Scanner(System.in);

	@Override
	public void pickUserFromHome(int date, User choosingUser) throws ParseException {
		// System suggestion
		Driver driver = (Driver) choosingUser;
		if (driver.getRouteFromHome().containsKey(date-1)) {
			System.out.println("You already have a rider at this day");
			
		} else {
			for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
			{
				User theUser = entry.getValue();
				if(theUser.getMemberSchedule().get(date-1).isAvailableHome()){
					if(choosingUser.getRegion() - entry.getValue().getRegion() >= 0)
					{
						if(choosingUser.getMemberSchedule().get(date-1).getHomeTime().equals(entry.getValue().getMemberSchedule().get(date-1).getHomeTime()))
						{
							System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
									" at " + format.format(choosingUser.getMemberSchedule().get(date-1).getHomeTime()));
						}
					}
				}
			}

			// Driver choose
			System.out.print("Enter the username you want to pickup or enter [0] to exit: ");

			String usernameChoice = sc.nextLine();
			Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
			if(rider != null && rider.getMemberSchedule().get(date-1).isAvailableHome())
			{
				rider.getMemberSchedule().get(date-1).setNotAvailableHome();
				choosingUser.addRideFromHome(date-1, SystemCaller.riderTable.get(usernameChoice));
				rider.addRideFromHome(date-1,choosingUser);
				System.out.println("Done, you may pick up: " + rider.getName());
				notifyObserver("Calling observer pattern - Driver: " + choosingUser.getName() + " will ride with " + rider.getName());
			}
			else
			{
				System.out.println("This rider isn't available");
			}
		}
		
	}

	@Override
	public void pickUserFromSchool(int date, User choosingUser) throws ParseException {
		Driver driver = (Driver) choosingUser;

		if (driver.getRouteFromSchool().containsKey(date-1)) {
			System.out.println("You already have a driver at this day");
			
		} else {
			for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
			{
				User theUser = entry.getValue();
				if(theUser.getMemberSchedule().get(date-1).isAvailableSchool())
				{
					if(choosingUser.getRegion() - entry.getValue().getRegion() >= 0)
					{
						if(choosingUser.getMemberSchedule().get(date-1).getSchoolTime().equals(entry.getValue().getMemberSchedule().get(date-1).getSchoolTime()))
						{
							System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
									" at " + format.format(choosingUser.getMemberSchedule().get(date-1).getSchoolTime()));
						}
					}
				}
			}

			System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
			String usernameChoice = sc.nextLine();
			Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
			if((rider != null) && (rider.getMemberSchedule().get(date-1).isAvailableSchool()))
			{
				rider.getMemberSchedule().get(date-1).setNotAvailableSchool();
				choosingUser.addRideFromSchool(date-1,rider); // add RIDER to this DRIVER
				rider.addRideFromSchool(date-1,choosingUser); // add THIS DRIVER to rider..
				System.out.println("Done, you may pick up: " + rider.getName());
				notifyObserver("Calling observer pattern - Driver: " + choosingUser.getName() + " will ride with " + rider.getName());
			} 
			else
			{
				System.out.println("This rider isn't available");
			}
		}
		
	}
}