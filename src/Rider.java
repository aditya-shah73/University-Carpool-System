import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A class for the rider who is a user of the system
 * @author Group 8
 */
public class Rider implements User 
{
	private Scanner sc = new Scanner(System.in);
	private ArrayList<String> notification;
	
	private int credit;
	private double cash;
	private int currentLocation;
	private MemberSchedule memberSchedule;
	private ArrayList<User> riderListSchool;
	private ArrayList<User> riderListHome;
	
	private String username;
	private String address;
	private String fullName;
	private int region;
	private int emptySeatFromSchool;
	private int emptySeatFromHome;
	private String departFromHome;
	private String departFromSchool;
	// This user's driver that will pickup FROM home/school
	User driverFromHome;
	User driverFromSchool;

	SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	
	public Rider(String username, String name, String address, int region, String departFromHome, String departFromSchool) throws ParseException
	{	
		// Initialize account payment credit/cash value
		this.credit = 20;
		this.cash = 20.0;
		// Initialize notification list
		this.notification = new ArrayList<>();
		// Initialize user basic information
		this.fullName = name;
		this.address = address;
		this.region = region;
		this.username = username;
		// Initialize time schedule and MemberSchedule
		this.departFromHome = departFromHome;
		this.departFromSchool = departFromSchool;
		this.memberSchedule = new MemberSchedule(departFromHome, departFromSchool);
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
	 * Pay Driver Cash
	 * @param driver
	 * @param distance
	 */
	public void payDriverCash(){
		
		Driver driver = null;
		int distance;
		
		if(this.driverFromHome!=null)
		{
			System.out.println("Driver from home: " + this.driverFromHome.getUsername()); 
		} 
		else
		{
			System.out.println("No ride from home available");
		}
		if (this.driverFromSchool != null)
		{
			System.out.println("Driver from school: " + this.driverFromSchool.getUsername());
		} 
		else
		{
			System.out.println("No ride from school available");
		}
		System.out.println();
		
		
		if (this.driverFromHome!= null || this.driverFromSchool != null) {

			System.out.print("Enter the username of driver you want to pay: ");
			String usernameChoice = sc.next();
			// Check available
			if(this.driverFromHome != null && usernameChoice.equals(this.driverFromHome.getUsername()))
			{
				driver = (Driver) driverFromHome;
				this.driverFromHome = null;
			} else if (this.driverFromSchool != null && usernameChoice.equals(this.driverFromSchool.getUsername())) {
				driver = (Driver) driverFromSchool;
				this.driverFromSchool = null;
			}
			else
			{
				System.out.println("This driver isn't available.");
			}
		
			System.out.print("Enter the total distance in integer format: ");
			distance = sc.nextInt();
		
			Payment payment = new PayCashConcrete((Driver) driver, distance);
			AbstractPaymentCaller paymentCaller = new PaymentCaller(payment);
			paymentCaller.executePay();
			this.cash -= distance * 0.5; // charge rider cash
		}
	}

	/**
	 * Pay Driver by rider's credit
	 * @param driver
	 * @param distance
	 */
	public void payDriverCredit(){
		Driver driver = null;
		int distance;
		
		if(this.driverFromHome!=null)
		{
			System.out.println("Driver from home: " + this.driverFromHome.getUsername()); 
		} 
		else
		{
			System.out.println("No ride from home available");
		}
		if (this.driverFromSchool != null)
		{
			System.out.println("Driver from school: " + this.driverFromSchool.getUsername());
		} 
		else
		{
			System.out.println("No ride from school available");
		}
		System.out.println();
		
		if (this.driverFromHome !=null || this.driverFromSchool != null) {

		System.out.print("Enter the username of driver you want to pay: ");
		String usernameChoice = sc.next();
		// Check available
		if(this.driverFromHome != null && usernameChoice.equals(this.driverFromHome.getUsername()))
		{
			driver = (Driver) driverFromHome;
			this.driverFromHome = null;

		} else if (this.driverFromSchool != null && usernameChoice.equals(this.driverFromSchool.getUsername())) {
			driver = (Driver) driverFromSchool;
			this.driverFromSchool = null;
		}
		else
		{
			System.out.print("This driver isn't available.");
		}
		
		System.out.println("Enter the total distance in integer format: ");
		distance = sc.nextInt();
		
		Payment payment = new PayCreditConcrete(driver, distance);
		AbstractPaymentCaller paymentCaller = new PaymentCaller(payment);
		paymentCaller.executePay();
		this.credit -= distance * 1; // charge rider credit
		}
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
	 * Display Driver that will pickup this rider From Home
	 */
	public void displayRideFromHome() 
	{
		if(this.driverFromHome!=null)
		{
			System.out.println("You will be picked up from home by: " + this.driverFromHome.getName() + "\tat " + 
					format.format(this.driverFromHome.getMemberSchedule().getHomeTime())); 
		} 
		else
		{
			System.out.println("No ride from home available");
		}
		System.out.println();
	}
	
	/**
	 * Display Driver that will pickup this rider From School
	 */
	public void displayRideFromSchool() 
	{
		if (this.driverFromSchool != null)
		{
			System.out.println("You will be picked up from school by: " + this.driverFromSchool.getName() + "\tat " + 
					format.format(this.driverFromSchool.getMemberSchedule().getSchoolTime())); 
		} 
		else
		{
			System.out.println("No ride from school available");
		}	
	}

	/**
	 * Add Driver that will pickup this rider FROM Home
	 */
	public boolean addRideFromHome(User user) throws ParseException
	{
		this.driverFromHome = user;
		user.addNotification("You will pick up " + this.getName() + " from home");
		this.addNotification("Your driver " + user.getName() + " will pick you up from Home");
		return true;
	}

	/**
	 * Add Driver that will pickup this rider FROM Home
	 */
	public boolean addRideFromSchool(User user) throws ParseException
	{
		this.driverFromSchool = user;
		user.addNotification("You will pick up " + this.getName() + " from school");
		this.addNotification("Your driver " + user.getName() + " will pick you up from School");
		return true;
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
	// Setter and getter end here
	
	public Driver getDriverFromHome(){
		return (Driver) this.driverFromHome;
	}
	
	public Driver getDriverFromSchool(){
		return (Driver) this.driverFromSchool;
	}

	@Override
	public void observersNotify(String message) {
		System.out.println(message);
	}

}