import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A class for the rider who is a user of the system
 * @author Group 8
 */
public class Rider implements User 
{
	private Payment payment;
	private ArrayList<String> notification;
	private Driver driverSchool = null;
	private Driver driverHome = null;
	private String address;
	private String fullName;
	private int region;
	private String username;
	private String departFromHome;
	private String departFromSchool;
	private MemberSchedule memberSchedule;
	private boolean availableHome;
	private boolean availableSchool;
	SimpleDateFormat format = new SimpleDateFormat("hh:mm");

	// Default Constructor
	public Rider() throws ParseException
	{
		this("", "", "", 0, "00:00", "00:00");
	}

	public Rider(String username, String name, String address, int region, String departFromHome, String departFromSchool) throws ParseException
	{	
		this.payment = new Payment();
		this.notification = new ArrayList<>();
		this.availableHome = true;
		this.availableSchool = true;
		this.fullName = name;
		this.address = address;
		this.region = region;
		this.username = username;
		this.departFromHome = departFromHome;
		this.departFromSchool = departFromSchool;
		this.memberSchedule = new MemberSchedule(departFromHome, departFromSchool);
	}
	// From Home
	public boolean addRideHome(User user) throws ParseException
	{
		this.driverHome = new Driver();
		this.driverHome = (Driver) user;
		
		this.addNotification("Your driver " + user.getName() + " will pick you up from Home");
		return true;
	}

	// From School
	public boolean addRideSchool(User user) throws ParseException
	{
		this.driverSchool = new Driver();
		this.driverSchool = (Driver) user;

		this.addNotification("Your driver " + user.getName() + " will pick you up from School");
		
		return true;
	}

	public void notAvailableHome()
	{
		this.availableHome = false;
	}

	public boolean isAvailableHome()
	{
		return this.availableHome;
	}

	public void notAvailableSchool()
	{
		this.availableSchool = false;
	}

	public boolean isAvailableSchool()
	{
		return this.availableSchool;
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getStatus()
	{
		return "Rider";
	}

	public String getAddress()
	{
		return this.address;
	}

	public String getName()
	{
		return this.fullName;
	}

	public int getRegion()
	{
		return this.region;
	}

	public void setAddress(String newAddress)
	{
		this.address = newAddress;
	}

	public void setMemberSchedule(MemberSchedule memberSchedule)
	{
		this.memberSchedule = memberSchedule;
	}

	public MemberSchedule getMemberSchedule()
	{
		return this.memberSchedule;
	}
	public void addDriver(){

	}

	public void displayNotification(){
		System.out.println("***Notification***");
		for(int i = 0; i < this.notification.size(); i++){
			System.out.println(this.notification.get(i));
		}
		System.out.println();
		
	}
	public void addNotification(String message){
		this.notification.add(message);
	}
	
	public void displayRideHome() 
	{
		if(this.driverHome!=null)
		{
			System.out.println("You will be picked up from home by: " + this.driverHome.getName() + "\tat " + 
					format.format(this.driverHome.getMemberSchedule().getHomeTime()));
		} 
		else
		{
			System.out.println("No ride from home available");
		}


	}

	public void displayRideSchool() 
	{
		if (this.driverSchool != null)
		{
			System.out.println("You will be picked up from school by: " + this.driverSchool.getName() + "\tat " + 
					format.format(this.driverSchool.getMemberSchedule().getSchoolTime()));
		} 
		else
		{
			System.out.println("No ride from school available");
		}
	}
}