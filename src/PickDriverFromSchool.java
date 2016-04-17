import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class PickDriverFromSchool extends TemplateRideSchedule{

	public PickDriverFromSchool(HashMap<String, User> table, User user) {
		super(table, user);
	}

	@Override
	protected void findAvailableUser() {
		// System suggestion
		for(Map.Entry<String, User> entry : SystemCaller.driverTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableSchool())
			{
				if(theUser.getRegion() - this.user.getRegion() >= 0)
				{
					if(this.user.getMemberSchedule().getSchoolTime().equals(theUser.getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may carpool with: " + theUser.getName() 
						+ " at " + format.format(this.user.getMemberSchedule().getSchoolTime())
						+ " \t[Username]: " + theUser.getUsername() );
					}
				}
			}
		}
		
	}

	@Override
	protected void displayAndPrompt() {
		System.out.print("Enter the username of driver you want to carpool with, [0] to exit: ");
		String usernameChoice = sc.nextLine();
		
		Driver driver = (Driver) SystemCaller.driverTable.get(usernameChoice);
		
		if((driver != null) && (driver.getMemberSchedule().isAvailableSchool()))
		{
			try {
				this.user.addRideFromHome(driver); // Add driver to pickup THIS rider
				driver.reserveOneSeatHome(this.user); // reserve one seat from Home
			} catch (ParseException e) {
				e.printStackTrace();
			} // add RIDER to this DRIVER
			
			System.out.println("\nDone !!! You will be picked up by: " + driver.getName());
		} 
		else
		{
			System.out.println("This rider isn't available. Please choose again.\n");
		}
	}

}
