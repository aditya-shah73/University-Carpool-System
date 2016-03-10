import java.text.ParseException;

public interface RideScheduleScheme 
{
	public void pickUserHome(User returnedUser)throws ParseException;
	public void pickUserSchool(User returnedUser)throws ParseException;
}
