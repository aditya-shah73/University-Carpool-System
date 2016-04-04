
public class PayCredit implements Payment{
	private Driver driver;
	private int distance;
	
	public PayCredit(Driver driver, int distance){
		this.distance = distance;
		this.driver = driver;
	}
	
	public void pay(){
		this.driver.addCredit(distance * 1);
	}
}
