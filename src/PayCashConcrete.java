
public class PayCashConcrete implements Payment {
	private Driver driver;
	private int distance;
	
	public PayCashConcrete(Driver driver, int distance){
		this.distance = distance;
		this.driver = driver;
	}
	
	public void pay(){
		this.driver.addCash(distance * 1);
	}
}
