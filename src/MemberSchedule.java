import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**The schedule for a member of the system
 * @author Group 8
 */
public class MemberSchedule 
{
	private Date departFromHome; // What time you need to be picked up from your house
	private Date departFromSchool; //What time do you need to be picked up from school
	private SimpleDateFormat format;
	
	private boolean isAvailableHome; // is Available Home to go from home ?
	private boolean isAvailableSchool; // is Available Home to go from school ?

	public MemberSchedule(String departHome, String departSchool) throws ParseException
	{
		this.format = new SimpleDateFormat("hh:mm");
		departFromHome = format.parse(departHome);
		departFromSchool = format.parse(departSchool);
		
		this.isAvailableHome = true;
		this.isAvailableSchool = true;
	}

	public Date getHomeTime()
	{
		return departFromHome;
	}

	public Date getSchoolTime()
	{
		return departFromSchool;
	}
	
	public void setNotAvailableHome(){
		this.isAvailableHome = false;
	}
	public boolean isAvailableHome(){
		return this.isAvailableHome;
	}
	
	public void setNotAvailableSchool(){
		this.isAvailableSchool = false;
	}
	public boolean isAvailableSchool(){
		return this.isAvailableSchool;
	}
}