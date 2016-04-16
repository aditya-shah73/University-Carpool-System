import java.text.ParseException;

public class SchemeContext {
	public RideScheduleScheme rideScheduleScheme;
	
	public SchemeContext() {
	}
	
	public void getScheme(User user, int userOption) throws ParseException {
		
		if (user.getStatus().equals("Driver")) {
			rideScheduleScheme = new DriverPickStrategy();
		} else {
			rideScheduleScheme = new RiderPickStrategy();
		}
	}
	public void executeScheme(User user, int userOption) throws ParseException {
		getScheme(user, userOption);
		if (userOption == 1) {
			rideScheduleScheme.pickUserFromSchool(user);
		} else {
			rideScheduleScheme.pickUserFromHome(user);
		}
	}

}
