import java.util.Date;

/**
 *
 * @author Linh
 */
public class MemberSchedule {
    private Date departFromHome;
    private Date departFromSchool;
    private Date arriveAtSchool;
    private Date arriveAtHome;
    
    public MemberSchedule(){
        departFromHome = new Date();
        departFromSchool = new Date();
        arriveAtHome = new Date();
        arriveAtSchool = new Date();
    }
    
    public void setDepartFromHomeTime(Date departTime){
        this.departFromHome = departTime;
    }
    
    public void setDepartFromSchoolTime(Date departTime){
        this.departFromSchool = departTime;
    }
    
    public void setArriveAtSchoolTime(Date arrivalTime){
        this.arriveAtSchool = arrivalTime;
    }
    public void setArriveAtHomeTime(Date arrivalTime){
        this.arriveAtHome = arrivalTime;
    }
}
