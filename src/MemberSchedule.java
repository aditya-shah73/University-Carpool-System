import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Group 8
 */
public class MemberSchedule 
{
	private Date departFromHome; // What time you need to be picked up from your house
	private Date departFromSchool; //What time do you need to be picked up from school
	private SimpleDateFormat format;

	public MemberSchedule(String departHome, String departSchool) throws ParseException
	{
		this.format = new SimpleDateFormat("hh:mm");
		departFromHome = format.parse(departHome);
		departFromSchool = format.parse(departSchool);
	}

	public Date getHomeTime()
	{
		return departFromHome;
	}

	public Date getSchoolTime()
	{
		return departFromSchool;
	}
}