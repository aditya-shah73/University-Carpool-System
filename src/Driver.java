import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A class for the driver who is a user of the system
 * @author Group 8 
 */
public class Driver implements User, RideScheduleScheme
{
	private Payment payment;
	private int currentLocation;
	private ArrayList<String> notification;
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
		this.payment = new Payment();
		this.currentLocation = 0;
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
		notification = new ArrayList<>();
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
		if (!riderListHome.isEmpty()) 
		{
			for(int i = 0; i< this.riderListHome.size(); i++)
			{
				System.out.println("Ride home with: " + riderListHome.get(i).getName() + " at " + 
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
				System.out.println("Ride to school with: " + riderListSchool.get(i).getName() + " at " +
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

	// Return array list of rider Driver have to pick up, by order 
	public ArrayList<User> getRouteFromHome()
	{
		this.currentLocation = this.region; // startingLocation = home
		Route routeFromHome = new Route(this.riderListHome);
		return routeFromHome.getRouteFromHome();
	}

	public ArrayList<User> getRouteFromSchool()
	{
		this.currentLocation = 0; // startingLocation = school
		Route routeFromSchool = new Route(this.riderListSchool);
		return routeFromSchool.getRouteFromSchool();
	}
	
	// Tracking driver's current Location
	public void setCurrentLocation(int location)
	{
		this.currentLocation = location;
	}
	
	public int getCurrentLocation()
	{
		return this.currentLocation;
	}

	// get Payment
	public Payment getPayment()
	{
		return this.payment;
	}

	public void pickUserSchool(User returnedUser) throws ParseException 
	{
		Scanner sc = new Scanner(System.in);
		for(Map.Entry<String, User> entry : MainSystem.riderTable.entrySet())
		{
			if(entry.getValue().isAvailableSchool())
			{
				if(returnedUser.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(returnedUser.getMemberSchedule().getSchoolTime().equals(entry.getValue().getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
								" at " + format.format(returnedUser.getMemberSchedule().getSchoolTime()));
					}
				}
			}
		}
		System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
		String usernameChoice = sc.nextLine();
		if((MainSystem.riderTable.get(usernameChoice) != null) && (MainSystem.riderTable.get(usernameChoice).isAvailableSchool()))
		{
			MainSystem.riderTable.get(usernameChoice).notAvailableSchool();
			returnedUser.addRideSchool(MainSystem.riderTable.get(usernameChoice));
			MainSystem.riderTable.get(usernameChoice).addRideSchool(returnedUser);
			System.out.println("Done, ride with: " + MainSystem.riderTable.get(usernameChoice).getName());
		} 
		else
		{
			System.out.println("This rider isn't available");
		}
	}

	public void pickUserHome(User returnedUser) throws ParseException 
	{
		Scanner sc = new Scanner(System.in);
		for(Map.Entry<String, User> entry : MainSystem.riderTable.entrySet())
		{
			if(entry.getValue().isAvailableHome()){
				if(returnedUser.getRegion() - entry.getValue().getRegion() >= 0)
				{
					if(returnedUser.getMemberSchedule().getHomeTime().equals(entry.getValue().getMemberSchedule().getHomeTime()))
					{
						System.out.println("You may pick up: " + entry.getValue().getName() + "  Username: " + entry.getValue().getUsername() +
								" at " + format.format(returnedUser.getMemberSchedule().getHomeTime()));
					}
				}
			}
		}
		System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
		String usernameChoice = sc.nextLine();
		if(MainSystem.riderTable.get(usernameChoice) != null)
		{
			MainSystem.riderTable.get(usernameChoice).notAvailableHome();
			returnedUser.addRideHome(MainSystem.riderTable.get(usernameChoice));
			MainSystem.riderTable.get(usernameChoice).addRideHome(returnedUser);
			System.out.println("Done, you may now ride with: " + MainSystem.riderTable.get(usernameChoice).getName());
		}
		else
		{
			System.out.println("This rider isn't available");
		}
	}
}