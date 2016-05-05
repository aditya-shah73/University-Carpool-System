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
	private ArrayList<MemberSchedule> memberSchedule;
	private ArrayList<User> riderListFromSchool;
	private ArrayList<User> riderListFromHome;
	
	private String username;
	private String address;
	private String fullName;
	private int region;
	private int emptySeatFromSchool;
	private int emptySeatFromHome;
	private ArrayList<String> departFromHome;
	private ArrayList<String> departFromSchool;
	private String parkingSpot;
	
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
	public Driver(String username, String name, String address, int region, ArrayList<String> departFromHome, ArrayList<String> departFromSchool, int seat) throws ParseException
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
		this.memberSchedule  = new ArrayList<>();
		for(int i=0; i<7; i++){
			this.memberSchedule.add(i,new MemberSchedule(departFromHome.get(i), departFromSchool.get(i)));
		}
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
	public void addCash(int amount){
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
	public void displayRideFromHome(int date)
	{
		if (!riderListFromHome.isEmpty()) 
		{
			System.out.println("List Of Rider to ride from home to school: ");
			for(int i = 0; i< this.riderListFromHome.size(); i++)
			{
				System.out.println(riderListFromHome.get(i).getName() + " at " + 
						format.format(riderListFromHome.get(i).getMemberSchedule().get(date).getHomeTime()));
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
	public void displayRideFromSchool(int date)
	{
		if (!riderListFromSchool.isEmpty()) 
		{
			System.out.println("List Of Rider to ride from SJSU to home: ");
			for(int i = 0; i< this.riderListFromSchool.size(); i++)
			{
				System.out.println(riderListFromSchool.get(i).getName() + " at " +
						format.format(riderListFromSchool.get(i).getMemberSchedule().get(date).getSchoolTime()));
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
	public boolean addRideFromHome(int date, User rider)
	{
		if(this.emptySeatFromHome > 0)
		{
			this.emptySeatFromHome--;
			this.riderListFromHome.add(rider);
			if(this.emptySeatFromHome == 0)
			{
				this.memberSchedule.get(date).setNotAvailableHome();
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
	public boolean addRideFromSchool(int date, User rider)
	{
		if(this.emptySeatFromSchool > 0)
		{
			this.emptySeatFromSchool--;
			this.riderListFromSchool.add(rider);
			if(this.emptySeatFromSchool == 0)
			{
				this.memberSchedule.get(date).setNotAvailableSchool();
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

	public void setMemberSchedule(ArrayList<MemberSchedule> memberSchedule)
	{
		this.memberSchedule = memberSchedule;
	}

	public ArrayList<MemberSchedule> getMemberSchedule()
	{
		return this.memberSchedule;
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
	public void reserveOneSeatHome(int date, User rider)
	{
		this.addRideFromHome(date, rider);
	}
	
	public void reserveOneSeatSchool(int date, User rider)
	{
		this.addRideFromSchool(date, rider);
	}
	
	public void setParkingSpot(String parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	
	public String getParkingSpot() {
		return this.parkingSpot;
	}
	

	@Override
	public void observersNotify(String message) {
		System.out.println(message);
		
	}
}