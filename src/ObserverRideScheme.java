import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;


public class ObserverRideScheme {
	public static ArrayList<User> userArrayList = new ArrayList<>();
	
	public ObserverRideScheme() {
	}
	public void addObserver(User user) {
		userArrayList.add(user);
	}
	void deleteObserver(User user) {
		userArrayList.remove(user);
	}
	public void notifyObserver(String message) {

		Iterator<User> observerIterator = userArrayList.iterator();
		
		while (observerIterator.hasNext()) {
			User user = (User) observerIterator.next();
			// user.observersNotify("NOTIFY USER " + user.getUsername() + " " + message);
		}
		
	}
}
