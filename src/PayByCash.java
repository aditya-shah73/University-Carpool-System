public class PayByCash extends Payment
{
	public PayByCash()
	{
		super();
	}
	
	public void addCash(double cash)
	{
		this.cash += cash;
	}
	
	public void payByCash(double cashPaid)
	{
		this.cash -= cashPaid;
		System.out.println("Paid by cash. Amount: $" + cashPaid);
	}
	
	public double getCash()
	{
		return this.cash;
	}
}