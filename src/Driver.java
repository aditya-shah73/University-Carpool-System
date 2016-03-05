import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A class for the driver who is a user of the system
 * @author Group 8 
 */
public class Driver implements User
{
	private ArrayList<User> riderListSchool;
	private ArrayList<User> riderListHome;
	private String address;
	private String fullName;
	private int region;
	private String username;
	private int availableSeat;
	private String departFromHome;
	private String departFromSchool;
	private MemberSchedule memberSchedule;
	private boolean availableHome;
	private boolean availableSchool;
	SimpleDateFormat format = new SimpleDateFormat("hh:mm");

	// Default Constructor
	public Driver() throws ParseException
	{
		this("", "", "", 0, "00:00", "00:00", 0);
	}

	public Driver(String username, String name, String address, int region, String departFromHome, String departFromSchool, int seat) throws ParseException
	{	
		this.availableHome = true;
		this.availableSchool = true;
		this.availableSeat = seat;
		this.riderListHome = new ArrayList<User>();
		this.riderListSchool = new ArrayList<User>();
		this.fullName = name;
		this.address = address;
		this.region = region;
		this.username = username;
		this.departFromHome = departFromHome;
		this.departFromSchool = departFromSchool;
		this.memberSchedule = new MemberSchedule(departFromHome, departFromSchool);
	}

	public void displayRideHome()
	{
		if (!riderListHome.isEmpty()) 
		{
			for(int i = 0; i< this.riderListHome.size(); i++)
			{
				System.out.println("Ride home with: " + riderListHome.get(i).getName() + "\tat " + 
						format.format(riderListHome.get(i).getMemberSchedule().getHomeTime()));
			}
		} 
		else
		{
			System.out.println("No rider home");
		}
	}

	public void displayRideSchool()
	{
		if (!riderListSchool.isEmpty()) 
		{
			for(int i = 0; i< this.riderListSchool.size(); i++)
			{
				System.out.println("Ride to school with: " + riderListSchool.get(i).getName() + "\tat " +
						format.format(riderListSchool.get(i).getMemberSchedule().getSchoolTime()));
			}
		} 
		else
		{
			System.out.println("No ride to school");
		}
	}

	public void notAvailableHome()
	{
		this.availableHome = false;
	}

	public void notAvailableSchool()
	{
		this.availableSchool = false;
	}

	public boolean isAvailableHome()
	{
		return this.availableHome;
	}

	public boolean isAvailableSchool()
	{
		return this.availableSchool;
	}

	public int getAvailableSeat()
	{
		return this.availableSeat;
	}

	public boolean addRideHome(User rider)
	{
		if(this.availableSeat > 0)
		{
			this.availableSeat--;
			this.riderListHome.add(rider);
			if(this.availableSeat == 0)
			{
				this.notAvailableHome();
			}
			return true;
		}  
		else
		{
			return false;
		}
	} 

	public boolean addRideSchool(User rider)
	{
		if(this.availableSeat > 0)
		{
			this.availableSeat--;
			this.riderListSchool.add(rider);
			if(this.availableSeat == 0)
			{
				this.notAvailableSchool();
			}
			return true;
		}  
		else
		{
			return false;
		}
	} 

	public String getUsername()
	{
		return this.username;
	}

	public String getStatus()
	{
		return "Driver";
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
}