import java.text.ParseException;

public class SchemeContext {
	public RideScheduleScheme rideScheduleScheme;
	
	public SchemeContext(RideScheduleScheme rideScheduleScheme) {
		this.rideScheduleScheme = rideScheduleScheme;
	}
	
	public void executeScheme(User user, int userOption) throws ParseException {
		
		if (user.getStatus().equals("Driver")) {
			if (userOption == 1) {
				rideScheduleScheme.pickUserSchool();
			} else {
				rideScheduleScheme.pickUserHome();
			}
		} else {
			if (userOption == 1) {
				rideScheduleScheme.pickUserSchool();
			} else {
				rideScheduleScheme.pickUserHome();
			}
		}
		
	}

}
