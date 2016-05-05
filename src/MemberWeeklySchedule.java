import java.util.HashMap;

public class MemberWeeklySchedule 
{
	HashMap<String, MemberSchedule> hm = new HashMap<>();
	
	public void addSchedule(String date, MemberSchedule m)
	{
		hm.put(date, m);
	}
	
	public MemberSchedule getMemberSchedule(String date)
	{
		return hm.get(date);
	}
}
