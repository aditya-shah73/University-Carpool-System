import java.text.ParseException;

/**
 * The user interface for different users
 * @author Group 8
 */
public interface User 
{
	public void observersNotify(String message);
	public double viewCash();
	public int viewCredit();
	public void addCash(int amount);
	public void addCredit(int amount);
	
	public void displayNotification();
	public void addNotification(String notification);
	
	public void displayRideFromHome();
	public void displayRideFromSchool();
	
	public boolean addRideFromHome(User user) throws ParseException;
	public boolean addRideFromSchool(User user) throws ParseException;
	/**
	 * Get Username
	 * @return String
	 */
	public String getUsername();
	
	/**
	 * Get Status
	 * @return String
	 */
	public String getStatus();
	public String getAddress();
	public String getName();
	public MemberSchedule getMemberSchedule();
	public int getRegion(); // A B C D
	public void setAddress(String newAddress);
	public void setMemberSchedule(MemberSchedule memberSchedule);
}