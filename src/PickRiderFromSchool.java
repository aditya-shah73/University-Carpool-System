import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class PickRiderFromSchool extends TemplateRideSchedule{

	public PickRiderFromSchool(HashMap<String, User> table, User user) {
		super(table, user);
	}

	@Override
	protected void findAvailableUser() {
		// System suggestion
		for(Map.Entry<String, User> entry : this.userTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableSchool())
			{
				if(this.user.getRegion() - theUser.getRegion() >= 0)
				{
					if(this.user.getMemberSchedule().getSchoolTime().equals(theUser.getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may pick up: " + theUser.getName() + 
								" at " + format.format(this.user.getMemberSchedule().getSchoolTime()) +
								" \t[Username]: " + theUser.getUsername());
					}
				}
			}
		}
		
	}

	@Override
	protected void displayAndPrompt() {
		System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
		String usernameChoice = sc.nextLine();
		
		Rider rider = (Rider) userTable.get(usernameChoice);
		if((rider != null) && (rider.getMemberSchedule().isAvailableSchool()))
		{
			rider.getMemberSchedule().setNotAvailableSchool();
			try {
				user.addRideFromSchool(rider);
				rider.addRideFromSchool(user); // add THIS DRIVER to rider.
			} catch (ParseException e) {
				e.printStackTrace();
			} // add RIDER to this DRIVER
			
			System.out.println("\nDone !!! You will drive to school with: " + rider.getName());
		} 
			
		else
		{
			System.out.println("This rider isn't available. Please choose again.\n");
		}
			

		
	}

}
