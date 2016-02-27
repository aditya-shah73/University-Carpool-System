/**
 *
 * @author Linh
 */
public interface User {
    public String getUsername(); 
    public String getStatus();
    public String getAddress();
    public String getName();
    public void setAddress(String newAddress);
    public int getRegion(); // A B C D
    public MemberSchedule getMemberSchedule();
    public void setMemberSchedule(MemberSchedule memberSchedule);
}
