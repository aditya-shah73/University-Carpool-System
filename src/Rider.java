import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A class for the rider who is a user of the system
 * @author Group 8
 */
public class Rider implements User, RideScheduleScheme 
{
	Scanner sc = new Scanner(System.in);
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
	public MainSystem admin = new MainSystem();
		
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
	
	public Payment getPayment()
	{
		return this.payment;
	}
	
	// From Home
	public boolean addRideHome(User user) throws ParseException
	{
		this.driverHome = (Driver) user;
		this.addNotification("Your driver " + user.getName() + " will pick you up from Home");
		return true;
	}

	// From School
	public boolean addRideSchool(User user) throws ParseException
	{
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
	
	public void addDriver()
	{

	}

	public void displayNotification()
	{
		System.out.println("***Notification***");
		for(int i = 0; i < this.notification.size(); i++)
		{
			System.out.println(this.notification.get(i));
		}
		System.out.println();
	}
	
	public void addNotification(String message)
	{
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

	// Chosing Driver to pick up THIS rider from Home
	public void pickUserHome() throws ParseException 
	{
		for(Map.Entry<String, User> entry : MainSystem.driverTable.entrySet())
		{
			if(entry.getValue().isAvailableHome())
			{
				if(this.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(this.getMemberSchedule().getHomeTime().equals(entry.getValue().getMemberSchedule().getHomeTime()))
					{
						System.out.println("You may carpool with: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
								" at " + format.format(this.getMemberSchedule().getHomeTime()));
					}
				}
			}
		}
		System.out.print(" Enter the username of driver you want to carpool with, [0] to exit: ");
		String usernameChoice = sc.nextLine();
		Driver driver = (Driver) MainSystem.driverTable.get(usernameChoice);
		// Check available
		if((driver != null) && (driver.isAvailableHome()))
		{
			this.addRideHome(driver); // Add driver to pickup THIS rider
			driver.reserveOneSeatHome(this); // reserve one seat from Home
			
			System.out.println("Done, ride with: " + driver.getName());
		} 
		else
		{
			System.out.println("This driver isn't available.");
		}

	}
	
	// Choosing Driver to pick up THIS rider from School
	public void pickUserSchool()throws ParseException
	{	
		for(Map.Entry<String, User> entry : MainSystem.driverTable.entrySet())
		{
			if(entry.getValue().isAvailableSchool())
			{
				if(this.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(this.getMemberSchedule().getSchoolTime().equals(entry.getValue().getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may carpool with: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
								" at " + format.format(this.getMemberSchedule().getSchoolTime()));
					}
				}
			}
		}
		System.out.print(" Enter the username of driver you want to carpool with, [0] to exit: ");
		String usernameChoice = sc.nextLine();
		Driver driver = (Driver) MainSystem.driverTable.get(usernameChoice);

		// Check driver available
		if((driver != null) && (driver.isAvailableSchool()))
		{
						
			this.addRideSchool(driver); // Add driver to pickup THIS rider.
			driver.reserveOneSeatSchool(this); // reserve one seat in driver
			
			System.out.println("Done, ride with: " + driver.getName());
		} 
		else
		{
			System.out.println("This driver isn't available.");
		}

	}
}