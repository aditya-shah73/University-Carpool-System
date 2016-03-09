import java.text.ParseException;
import java.util.ArrayList;

/**
 * The user interface for different users
 * @author Group 8
 */
public interface User 
{
	public Payment getPayment();
	public void displayNotification();
	public void displayRideHome();
	public void displayRideSchool();
	public boolean addRideHome(User user) throws ParseException;
	public boolean addRideSchool(User user) throws ParseException;
	public void notAvailableHome();
	public boolean isAvailableHome();
	public void notAvailableSchool();
	public boolean isAvailableSchool();
	public String getUsername(); 
	public String getStatus();
	public String getAddress();
	public String getName();
	public MemberSchedule getMemberSchedule();
	public int getRegion(); // A B C D
	public void setAddress(String newAddress);
	public void setMemberSchedule(MemberSchedule memberSchedule);
}