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
	public void pickUserFromHome(User choosingUser) throws ParseException {
		// System suggestion
				for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
				{
					User theUser = entry.getValue();
					if(theUser.getMemberSchedule().isAvailableHome()){
						if(choosingUser.getRegion() - entry.getValue().getRegion() >= 0)
						{
							if(choosingUser.getMemberSchedule().getHomeTime().equals(entry.getValue().getMemberSchedule().getHomeTime()))
							{
								System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
										" at " + format.format(choosingUser.getMemberSchedule().getHomeTime()));
							}
						}
					}
				}

				// Driver choose
				System.out.print("Enter the username you want to pickup or enter [0] to exit: ");

				String usernameChoice = sc.nextLine();
				Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
				if(rider != null && rider.getMemberSchedule().isAvailableHome())
				{
					rider.getMemberSchedule().setNotAvailableHome();
					choosingUser.addRideFromHome(SystemCaller.riderTable.get(usernameChoice));
					rider.addRideFromHome(choosingUser);
					System.out.println("Done, you may pick up: " + rider.getName());
					notifyObserver("Calling observer pattern - Driver: " + choosingUser.getName() + " will ride with " + rider.getName());
				}
				else
				{
					System.out.println("This rider isn't available");
				}
		
	}

	@Override
	public void pickUserFromSchool(User choosingUser) throws ParseException {
		
		for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableSchool())
			{
				if(choosingUser.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(choosingUser.getMemberSchedule().getSchoolTime().equals(entry.getValue().getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
								" at " + format.format(choosingUser.getMemberSchedule().getSchoolTime()));
					}
				}
			}
		}

		System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
		String usernameChoice = sc.nextLine();
		Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
		if((rider != null) && (rider.getMemberSchedule().isAvailableSchool()))
		{
			rider.getMemberSchedule().setNotAvailableSchool();
			choosingUser.addRideFromSchool(rider); // add RIDER to this DRIVER
			rider.addRideFromSchool(choosingUser); // add THIS DRIVER to rider..
			System.out.println("Done, you may pick up: " + rider.getName());
			notifyObserver("Calling observer pattern - Driver: " + choosingUser.getName() + " will ride with " + rider.getName());
		} 
		else
		{
			System.out.println("This rider isn't available");
		}
		
	}
	




}
