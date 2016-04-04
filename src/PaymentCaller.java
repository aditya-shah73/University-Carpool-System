
public class PaymentCaller {
	Payment payment;

	public PaymentCaller(Payment payment){
		this.payment = payment;
	
	}
	
	public void execute(){
		this.payment.pay();
	}
	
	
}
