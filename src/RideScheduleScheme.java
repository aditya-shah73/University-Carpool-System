import java.text.ParseException;

public interface RideScheduleScheme 
{
	public void pickUserFromHome(User choosingUser)throws ParseException;
	public void pickUserFromSchool(User choosingUser)throws ParseException;
}
