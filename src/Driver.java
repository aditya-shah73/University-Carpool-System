import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A class for the driver who is a user of the system
 * @author Group 8 
 */
public class Driver implements User
{
	private Scanner sc = new Scanner(System.in);
	private ArrayList<String> notification;
	
	private int credit;
	private double cash;
	
	private int currentLocation;
	private MemberSchedule memberSchedule;
	private ArrayList<User> riderListFromSchool;
	private ArrayList<User> riderListFromHome;
	
	private String username;
	private String address;
	private String fullName;
	private int region;
	private int emptySeatFromSchool;
	private int emptySeatFromHome;
	private String departFromHome;
	private String departFromSchool;
	
	RideStatusInterface rideStatus = new RideStatus();
	SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	/**
	 * Constructor
	 * @param username
	 * @param name
	 * @param address
	 * @param region
	 * @param departFromHome
	 * @param departFromSchool
	 * @param seat
	 * @throws ParseException
	 */
	public Driver(String username, String name, String address, int region, String departFromHome, String departFromSchool, int seat) throws ParseException
	{	
		// Initialize account payment credit/cash value
		this.credit = 20;
		this.cash = 20.0;
		// Initialize user basic information
		this.currentLocation = 0;
		this.emptySeatFromHome = seat;
		this.emptySeatFromSchool = seat;
		this.riderListFromHome = new ArrayList<User>();
		this.riderListFromSchool = new ArrayList<User>();
		this.fullName = name;
		this.address = address;
		this.region = region;
		this.username = username;
		// Initialize time schedule
		this.departFromHome = departFromHome;
		this.departFromSchool = departFromSchool;
		// Initialize MemberSchedule 
		this.memberSchedule = new MemberSchedule(departFromHome, departFromSchool);
		// Initialize notification list
		notification = new ArrayList<>();
	}

	/**
	 * View the amount of cash in the account.
	 * @return double account's cash
	 */
	public double viewCash(){
		double cashClone = this.cash;
		return cashClone;
	}
	
	/**
	 * View the amount of credit in the account.
	 * @return int account's credit
	 */
	public int viewCredit(){
		int creditClone = this.credit;
		return creditClone;
	}
	
	/**
	 * Add cash to account
	 * @param amount double
	 */
	public void addCash(double amount){
		this.cash += amount;
	}
	
	/**
	 * Add Credit to account
	 * @param amount int
	 */
	public void addCredit(int amount){
		this.credit += amount;
	}
	
	/**
	 * Display List Of Notification of this user
	 */
	public void displayNotification()
	{
		System.out.println("***Notification***");
		for(int i = 0; i < this.notification.size(); i++)
		{
			System.out.println(this.notification.get(i));
		}
		System.out.println();
	}

	/*
	 * Add Notification to this user 
	 */
	public void addNotification(String message)
	{
		this.notification.add(message);
	}

	/**
	 * Display Scheduled Ride From Home
	 */
	public void displayRideFromHome()
	{
		if (!riderListFromHome.isEmpty()) 
		{
			System.out.println("List Of Rider to ride from home to school: ");
			for(int i = 0; i< this.riderListFromHome.size(); i++)
			{
				System.out.println(riderListFromHome.get(i).getName() + " at " + 
						format.format(riderListFromHome.get(i).getMemberSchedule().getHomeTime()));
			}
		} 
		else
		{
			System.out.println("No rider to go from home to school.");
		}
		System.out.println();
	}
	
	/**
	 * Display Scheduled Ride From School
	 */
	public void displayRideFromSchool()
	{
		if (!riderListFromSchool.isEmpty()) 
		{
			System.out.println("List Of Rider to ride from SJSU to home: ");
			for(int i = 0; i< this.riderListFromSchool.size(); i++)
			{
				System.out.println(riderListFromSchool.get(i).getName() + " at " +
						format.format(riderListFromSchool.get(i).getMemberSchedule().getSchoolTime()));
			}
		} 
		else
		{
			System.out.println("No rider to go from School to home.");
		}
		System.out.println();
	}

	/**
	 * Add rider to drive with, go FROM home
	 */
	public boolean addRideFromHome(User rider)
	{
		if(this.emptySeatFromHome > 0)
		{
			this.emptySeatFromHome--;
			this.riderListFromHome.add(rider);
			if(this.emptySeatFromHome == 0)
			{
				this.memberSchedule.setNotAvailableHome();
			}
			return true;
		}  
		else
		{
			return false;
		}
	} 

	/**
	 * Add rider to drive with, go FROM school
	 * @param rider
	 * @return
	 */
	public boolean addRideFromSchool(User rider)
	{
		if(this.emptySeatFromSchool > 0)
		{
			this.emptySeatFromSchool--;
			this.riderListFromSchool.add(rider);
			if(this.emptySeatFromSchool == 0)
			{
				this.memberSchedule.setNotAvailableSchool();
			}
			return true;
		}  
		else
		{
			return false;
		}
	} 

	/**
	 * Setter and getter of basic information
	 */
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
	// Setter and getter end here


	// THIS DRIVER choose RIDER to pick up from HOME
	public void pickUserFromHome() throws ParseException 
	{
		// System suggestion
		for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableHome()){
				if(this.getRegion() - theUser.getRegion() >= 0)
				{
					if(this.getMemberSchedule().getHomeTime().equals(theUser.getMemberSchedule().getHomeTime()))
					{
						System.out.println("You may pick up: " + theUser.getName() + 
								" at " + format.format(this.getMemberSchedule().getHomeTime()) +
								" \t[Username]: " + theUser.getUsername());
					}
				}
			}
		}

		// Driver choose
		System.out.print("\nEnter the username you want to pickup or enter [0] to exit: ");

		String usernameChoice = sc.nextLine();
		
		Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
		if(rider != null && rider.getMemberSchedule().isAvailableHome())
		{
			rider.getMemberSchedule().setNotAvailableHome();
			this.addRideFromHome(SystemCaller.riderTable.get(usernameChoice));
			rider.addRideFromHome(this);
			System.out.println("\nDone !!! You will drive home with: " + rider.getName());
		}
		else
		{
			System.out.println("This rider isn't available. Please choose again.\n");
		}
		
		
	}

	// THIS DRIVER choose RIDER to pick up from SCHOOL
	public void pickUserFromSchool() throws ParseException 
	{
		for(Map.Entry<String, User> entry : SystemCaller.riderTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableSchool())
			{
				if(this.getRegion() - theUser.getRegion() >= 0)
				{
					if(this.getMemberSchedule().getSchoolTime().equals(theUser.getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may pick up: " + theUser.getName() + 
								" at " + format.format(this.getMemberSchedule().getSchoolTime()) +
								" \t[Username]: " + theUser.getUsername());
					}
				}
			}
		}

		System.out.print("Enter the username you want to pickup or enter [0] to exit: ");
		String usernameChoice = sc.nextLine();
		
		Rider rider = (Rider) SystemCaller.riderTable.get(usernameChoice);
		if((rider != null) && (rider.getMemberSchedule().isAvailableSchool()))
		{
			rider.getMemberSchedule().setNotAvailableSchool();
			this.addRideFromSchool(rider); // add RIDER to this DRIVER
			rider.addRideFromSchool(this); // add THIS DRIVER to rider.
			System.out.println("\nDone !!! You will drive to school with: " + rider.getName());
		} 
			
		else
		{
			System.out.println("This rider isn't available. Please choose again.\n");
		}
			
			
		
	}	
	
	
	// Return array list of rider Driver have to pick up, by order 
	public ArrayList<User> getRouteFromHome()
	{
		Route routeFromHome = new Route(this.riderListFromHome);
		return routeFromHome.getRouteFromHome();
	}

	public ArrayList<User> getRouteFromSchool()
	{
		Route routeFromSchool = new Route(this.riderListFromSchool);
		return routeFromSchool.getRouteFromSchool();
	}

	/**
	 * Set driver current location
	 * @param location
	 */
	public void setCurrentLocation(int location)
	{
		this.currentLocation = location;
	}

	/**
	 * Get Driver current Location
	 * @return int current location
	 */
	public int getCurrentLocation()
	{
		return this.currentLocation;
	}
	
	public void setStatus(RideState rideState){
		this.rideStatus.setCurrentStatus(rideState);
	}
	public void displayStatus(){
		this.rideStatus.getCurrentStatus().displayCurrentState();
	}
	// For RIDER to reserve seat
	public void reserveOneSeatHome(User rider)
	{
		this.addRideFromHome(rider);
	}
	
	public void reserveOneSeatSchool(User rider)
	{
		this.addRideFromSchool(rider);
	}

	@Override
	public void observersNotify(String message) {
		System.out.println(message);
		
	}
}