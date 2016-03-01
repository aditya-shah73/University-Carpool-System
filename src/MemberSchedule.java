import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Group 8
 */
public class MemberSchedule 
{
    private Date departFromHome; // What time you need to be picked up from your house
    private Date departFromSchool; //What time do you need to be picked up from school
//    private Date arriveAtSchool; 
//    private Date arriveAtHome;
    private SimpleDateFormat format;
    
    // Constructor 
    public MemberSchedule(String departHome, String departSchool) throws ParseException
    {
    	this.format = new SimpleDateFormat("hh:mm");
        departFromHome = format.parse(departHome);
        departFromSchool = format.parse(departSchool);
//        arriveAtHome = format.parse(arriveHome);
//        arriveAtSchool = format.parse(arriveSchool);
    }
    
    public Date getHomeTime()
    {
        return departFromHome;
    }
    
    public Date getSchoolTime()
    {
 	   return departFromSchool;
    }
    
//    public void setDepartFromHomeTime(Date departTime)
//    {
//        this.departFromHome = departTime;
//    }
//    
//    public void setDepartFromSchoolTime(Date departTime)
//    {
//        this.departFromSchool = departTime;
//    }
//    
//    public void setArriveAtSchoolTime(Date arrivalTime)
//    {
//        this.arriveAtSchool = arrivalTime;
//    }
//    
//    public void setArriveAtHomeTime(Date arrivalTime)
//    {
//        this.arriveAtHome = arrivalTime;
//    }
}