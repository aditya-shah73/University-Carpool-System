
public class PayCashConcrete implements Payment {
	private Driver driver;
	private int distance;
	
	public PayCashConcrete(Driver driver, int distance){
		this.distance = distance;
		this.driver = driver;
	}
	
	public void pay(){
		this.driver.addCash(distance * 1);
		System.out.println("You have successfully paid: $" + (distance * 1));
	}
}
