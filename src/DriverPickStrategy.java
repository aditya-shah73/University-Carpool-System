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
				for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
				{
					User theUser = entry.getValue();
					if(theUser.getMemberSchedule().get(date).isAvailableHome()){
						if(choosingUser.getRegion() - entry.getValue().getRegion() >= 0)
						{
							if(choosingUser.getMemberSchedule().get(date).getHomeTime().equals(entry.getValue().getMemberSchedule().get(date).getHomeTime()))
							{
								System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
										" at " + format.format(choosingUser.getMemberSchedule().get(date).getHomeTime()));
							}
						}
					}
				}

				// Driver choose
				System.out.print("Enter the username you want to pickup or enter [0] to exit: ");

				String usernameChoice = sc.nextLine();
				Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
				if(rider != null && rider.getMemberSchedule().get(date).isAvailableHome())
				{
					rider.getMemberSchedule().get(date).setNotAvailableHome();
					choosingUser.addRideFromHome(date, SystemCaller.riderTable.get(usernameChoice));
					rider.addRideFromHome(date,choosingUser);
					System.out.println("Done, you may pick up: " + rider.getName());
					notifyObserver("Calling observer pattern - Driver: " + choosingUser.getName() + " will ride with " + rider.getName());
				}
				else
				{
					System.out.println("This rider isn't available");
				}
		
	}

	@Override
	public void pickUserFromSchool(int date, User choosingUser) throws ParseException {
		
		for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().get(date).isAvailableSchool())
			{
				if(choosingUser.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(choosingUser.getMemberSchedule().get(date).getSchoolTime().equals(entry.getValue().getMemberSchedule().get(date).getSchoolTime()))
					{
						System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
								" at " + format.format(choosingUser.getMemberSchedule().get(date).getSchoolTime()));
					}
				}
			}
		}

		System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
		String usernameChoice = sc.nextLine();
		Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
		if((rider != null) && (rider.getMemberSchedule().get(date).isAvailableSchool()))
		{
			rider.getMemberSchedule().get(date).setNotAvailableSchool();
			choosingUser.addRideFromSchool(date,rider); // add RIDER to this DRIVER
			rider.addRideFromSchool(date,choosingUser); // add THIS DRIVER to rider..
			System.out.println("Done, you may pick up: " + rider.getName());
			notifyObserver("Calling observer pattern - Driver: " + choosingUser.getName() + " will ride with " + rider.getName());
		} 
		else
		{
			System.out.println("This rider isn't available");
		}
		
	}
}