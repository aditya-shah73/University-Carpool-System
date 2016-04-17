import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TemplateRideSchedule {
	public HashMap<String, User> userTable;
	public User user;
	public SimpleDateFormat format = new SimpleDateFormat("hh:mm");
	public Scanner sc = new Scanner(System.in);
	
	public TemplateRideSchedule(HashMap<String,User> table, User user){
		this.userTable = table;
		this.user = user;
	}
	
	protected abstract void findAvailableUser() ;
	protected abstract void displayAndPrompt();
	
	public void pickUser(){
		findAvailableUser();
		displayAndPrompt();
	}

}
