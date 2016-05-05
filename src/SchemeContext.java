import java.text.ParseException;

public class SchemeContext {
	public RideScheduleScheme rideScheduleScheme;
	
	public SchemeContext() {
	}
	
	public void getScheme(int date, User user, int userOption) throws ParseException {
		
		if (user.getStatus().equals("Driver")) {
			rideScheduleScheme = new DriverPickStrategy();
		} else {
			rideScheduleScheme = new RiderPickStrategy();
		}
	}
	public void executeScheme(int date, User user, int userOption) throws ParseException {
		getScheme(date, user, userOption);
		if (userOption == 1) {
			rideScheduleScheme.pickUserFromSchool(date, user);
		} else {
			rideScheduleScheme.pickUserFromHome(date, user);
		}
	}

}
