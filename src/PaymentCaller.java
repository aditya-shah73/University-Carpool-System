public class PaymentCaller extends AbstractPaymentCaller 
{
	public PaymentCaller(Payment payment) {
		super(payment);
	}
	@Override
	public void pay() {
		executePay();
	}
}