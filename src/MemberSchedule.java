import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Group 8
 */
public class MemberSchedule 
{
    private Date departFromHome;
    private Date departFromSchool;
    private Date arriveAtSchool;
    private Date arriveAtHome;
    private SimpleDateFormat format;
    
    
    public MemberSchedule(String departHome, String departSchool, String arriveHome, String arriveSchool) throws ParseException
    {
    	this.format = new SimpleDateFormat("hh:mmaa");
        departFromHome = format.parse(departHome);
        departFromSchool = format.parse(departSchool);
        arriveAtHome = format.parse(arriveHome);
        arriveAtSchool = format.parse(arriveSchool);
    }
    
    public void setDepartFromHomeTime(Date departTime)
    {
        this.departFromHome = departTime;
    }
    
    public void setDepartFromSchoolTime(Date departTime)
    {
        this.departFromSchool = departTime;
    }
    
    public void setArriveAtSchoolTime(Date arrivalTime)
    {
        this.arriveAtSchool = arrivalTime;
    }
    
    public void setArriveAtHomeTime(Date arrivalTime)
    {
        this.arriveAtHome = arrivalTime;
    }
}
