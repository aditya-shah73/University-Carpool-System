/**
 * The user interface for different users
 * @author Group 8
 */
public interface User 
{
	public boolean addRider(User rider);
	public void notAvailable();
	public boolean isAvailable();
	public String getUsername(); 
	public String getStatus();
	public String getAddress();
	public String getName();
	public MemberSchedule getMemberSchedule();
	public int getRegion(); // A B C D
	public void setAddress(String newAddress);
	public void setMemberSchedule(MemberSchedule memberSchedule);
}