
abstract class AbstractPaymentCaller {
	protected Payment payment;

	public AbstractPaymentCaller(Payment payment){
		this.payment = payment;
	}
	public abstract void pay();
	protected void executePay(){
		this.payment.pay();
	}
}
