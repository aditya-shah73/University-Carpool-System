import java.text.ParseException;

/**
* A class for the rider who is a user of the system
* @author Group 8
*/
public class Rider implements User 
{
   private String address;
   private String fullName;
   private int region;
   private String username;
   private String departFromHome;
   private String departFromSchool;
   private MemberSchedule memberSchedule;
   private boolean available;
   
   // Default Constructor
   public Rider() throws ParseException
   {
       this("", "", "", 0, "", "");
   }
   
 
   public Rider(String username, String name, String address, int region, String departFromHome, String departFromSchool) throws ParseException
   {	
	   this.available = true;
       this.fullName = name;
       this.address = address;
       this.region = region;
       this.username = username;
       this.departFromHome = departFromHome;
       this.departFromSchool = departFromSchool;
       this.memberSchedule = new MemberSchedule(departFromHome, departFromSchool);
   }
   public boolean addRider(User rider){
	   return false;
   }
   public void notAvailable(){
	   this.available = false;
   }
   public boolean isAvailable(){
	   return this.available;
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
}