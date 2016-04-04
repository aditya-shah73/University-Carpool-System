public class PayByCash extends AbstractPaymentCaller 
{
	
	public PayByCash(Payment payment) {
		super(payment);
	}
	@Override
	public void pay() {
		executePay();
	}
}