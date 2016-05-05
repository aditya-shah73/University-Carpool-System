import java.text.ParseException;

public interface RideScheduleScheme 
{
	public void pickUserFromHome(int date,User choosingUser)throws ParseException;
	public void pickUserFromSchool(int date, User choosingUser)throws ParseException;
}
