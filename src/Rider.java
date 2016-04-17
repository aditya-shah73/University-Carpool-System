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
	 * Pay Driver Cash
	 * @param driver
	 * @param distance
	 */
	public void payDriverCash(Driver driver, int distance){
		Payment payment = new PayCashConcrete(driver, distance);
		AbstractPaymentCaller paymentCaller = new PaymentCaller(payment);
		paymentCaller.executePay();
		
		this.cash -= distance * 0.5; // charge rider cash
		
	}

	/**
	 * Pay Driver by rider's credit
	 * @param driver
	 * @param distance
	 */
	public void payDriverCredit(Driver driver, int distance){
		Payment payment = new PayCreditConcrete(driver, distance);
		AbstractPaymentCaller paymentCaller = new PaymentCaller(payment);
		paymentCaller.executePay();
		
		this.credit -= distance * 1; // charge rider credit
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
		System.out.println();
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
	
	/**
	 * Chose Driver to Pick up THIS rider from home
	 */
	public void pickUserFromHome() throws ParseException 
	{
		TemplateRideSchedule pickFromHome = new PickDriverFromSchool(SystemCaller.riderTable, this);
		pickFromHome.pickUser();
		/*
		for(Map.Entry<String, User> entry : SystemCaller.driverTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableHome())
			{
				if(theUser.getRegion() - this.getRegion() >= 0)
				{
					if(this.getMemberSchedule().getHomeTime().equals(theUser.getMemberSchedule().getHomeTime()))
					{
						System.out.println("You may carpool with: " + theUser.getName() 
						+ " at " + format.format(this.getMemberSchedule().getHomeTime())
						+ " \t[Username]: " + theUser.getUsername() );
					}
				}
			}
		}
		System.out.print("Enter the username of driver you want to carpool with, [0] to exit: ");
		String usernameChoice = sc.nextLine();
		
		Driver driver = (Driver) SystemCaller.driverTable.get(usernameChoice);
		// Check available
		if((driver != null) && (driver.getMemberSchedule().isAvailableHome()))
		{
			this.addRideFromHome(driver); // Add driver to pickup THIS rider
			driver.reserveOneSeatHome(this); // reserve one seat from Home

			System.out.println("\nDone !!! You will be picked up by: " + driver.getName());
		}
		else
		{
			System.out.println("This driver isn't available. Please choose again.\n");
		}
		*/
	}

	/**
	 * Chose Driver to Pick up THIS rider FROM school
	 */
	public void pickUserFromSchool()throws ParseException
	{	
		TemplateRideSchedule pickFromSchool = new PickDriverFromSchool(SystemCaller.riderTable, this);
		pickFromSchool.pickUser();
		/*
		for(Map.Entry<String, User> entry : SystemCaller.driverTable.entrySet())
		{
			User theUser = entry.getValue();
			if(theUser.getMemberSchedule().isAvailableSchool())
			{
				if(theUser.getRegion() - this.getRegion() >= 0)
				{
					if(this.getMemberSchedule().getSchoolTime().equals(theUser.getMemberSchedule().getSchoolTime()))
					{
						System.out.println("You may carpool with: " + theUser.getName() 
						+ " at " + format.format(this.getMemberSchedule().getSchoolTime())
						+ " \t[Username]: " + theUser.getUsername() );
					}
				}
			}
		}
		System.out.print("Enter the username of driver you want to carpool with, [0] to exit: ");
		String usernameChoice = sc.nextLine();
		
		Driver driver = (Driver) SystemCaller.driverTable.get(usernameChoice);
		// Check available
		if((driver != null) && (driver.getMemberSchedule().isAvailableSchool()))
		{
			this.addRideFromHome(driver); // Add driver to pickup THIS rider
			driver.reserveOneSeatHome(this); // reserve one seat from Home
			
			System.out.println("\nDone !!! You will be picked up by: " + driver.getName());
		} 
			
		else
		{
			System.out.println("This driver isn't available. Please choose again.\n");
		}
		
		*/
	}
	
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