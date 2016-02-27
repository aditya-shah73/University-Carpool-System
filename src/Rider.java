/**
*
* @author Linh
*/
public class Rider implements User {
   private String address;
   private String fullName;
   private int region;
   private String username;
   private MemberSchedule memberSchedule;
   
   // Default Constructor
   public Rider(){
       this("", "", "", 0);
   }
   public Rider(String username, String name, String address, int region){
       this.fullName = name;
       this.address = address;
       this.region = region;
       this.username = username;
       this.memberSchedule = new MemberSchedule();
   }
   public String getUsername(){
       return this.username;
   }
   public String getStatus(){
       return "Rider";
   }
   
   public String getAddress(){
       return this.address;
   }
   
   public String getName(){
       return this.fullName;
   }
   
   public int getRegion(){
       return this.region;
   }
   public void setAddress(String newAddress){
       this.address = newAddress;
   }
   
   public void setMemberSchedule(MemberSchedule memberSchedule){
       this.memberSchedule = memberSchedule;
   }
   public MemberSchedule getMemberSchedule(){
       return this.memberSchedule;
   }
}
