
public class PayCreditConcrete implements Payment{
	private Driver driver;
	private int distance;
	
	public PayCreditConcrete(Driver driver, int distance){
		this.distance = distance;
		this.driver = driver;
	}
	
	public void pay(){
		this.driver.addCredit(distance * 1);
	}
}
