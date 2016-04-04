public class PayByCredit extends AbstractPaymentCaller 
{
	public PayByCredit(Payment payment) {
		super(payment);
	}
	@Override
	public void pay() {
		executePay();
	}
	
}
