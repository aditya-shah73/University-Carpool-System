import java.text.ParseException;
import java.util.ArrayList;

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
	
	public void displayRideFromHome(int date);
	public void displayRideFromSchool(int date);
	
	public boolean addRideFromHome(int date,User user) throws ParseException;
	public boolean addRideFromSchool(int date,User user) throws ParseException;
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
	public ArrayList<MemberSchedule> getMemberSchedule();
	public int getRegion(); // A B C D
	public void setAddress(String newAddress);
	public void setMemberSchedule(ArrayList<MemberSchedule> memberSchedule);
}