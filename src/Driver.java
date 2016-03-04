import java.text.ParseException;
import java.util.ArrayList;

/**
* A class for the driver who is a user of the system
* @author Group 8 
*/
public class Driver implements User
{
	private ArrayList<User> riderList;
   private String address;
   private String fullName;
   private int region;
   private String username;
   private int availableSeat;
   private String departFromHome;
   private String departFromSchool;
   private MemberSchedule memberSchedule;
   private boolean available;
   // Default Constructor
   public Driver() throws ParseException
   {
       this("", "", "", 0, "07:00", "12:00", 0);
   }
   
   public Driver(String username, String name, String address, int region, String departFromHome, String departFromSchool, int seat) throws ParseException
   {	
	   this.available = true;
	   this.availableSeat = seat;
	   this.riderList = new ArrayList<User>();
       this.fullName = name;
       this.address = address;
       this.region = region;
       this.username = username;
       this.departFromHome = departFromHome;
       this.departFromSchool = departFromSchool;
       this.memberSchedule = new MemberSchedule(departFromHome, departFromSchool);
   }
   public void notAvailable(){
	   this.available = false;
   }
   public boolean isAvailable(){
	   return this.available;
   }
   public int getAvailableSeat(){
	   return this.availableSeat;
   }
   public boolean addRider(User rider){
	   
	   if(this.availableSeat > 0){
		   this.availableSeat--;
		   this.riderList.add(rider);
		   if(this.availableSeat == 0){
			   this.notAvailable();
		   }
		   return true;
	   }
		  
	   else{
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
